#
# parsing maven pom.xml
# artifactId & version
#
import threading
from queue import Queue
import subprocess
from xml.etree import ElementTree
import sys


POM_FILE = sys.argv[1]
namespaces = {"xmlns": "http://maven.apache.org/POM/4.0.0"}

tree = ElementTree.parse(POM_FILE)
root = tree.getroot()

props = {}
for p in root.findall(".//xmlns:properties", namespaces=namespaces)[0]:
    props[p.tag[len(namespaces["xmlns"]) + 2 :]] = p.text

deps = []
for d in root.findall(".//xmlns:dependency", namespaces=namespaces):
    artifactId = d.find("xmlns:artifactId", namespaces=namespaces)
    groupId = d.find("xmlns:groupId", namespaces=namespaces)
    version = d.find("xmlns:version", namespaces=namespaces)

    if version.text[2:-1] in props:
        version = props[version.text[2:-1]]
    else:
        version = version.text
    deps.append(
        f"-Dartifact={groupId.text}:{artifactId.text}:{version}:jar",
    )

q = Queue()


def worker(q):
    while not q.empty():
        try:
            artifact = q.get()
            cmd = [
                "mvn",
                "dependency:get",
                "".join(artifact),
                "-Dtransitive=true",
                "-DoutputDirectory=/root/.m2",
                "--fail-never",
            ]
            subprocess.run(cmd)
            # cmd = [
            #     "mvn",
            #     "dependency:copy",
            #     "".join(artifact),
            #     "-Dtransitive=true",
            #     "-DoutputDirectory=/root/.m2",
            #     "--fail-never",
            # ]
            # subprocess.run(cmd)
            q.task_done()
        except OSError as err:
            print(err)
            if cmd is not None:
                q.put(cmd)


[q.put_nowait(i) for i in deps]

max_workers = 1
workers = [
    threading.Thread(
        target=worker,
        args=[
            q,
        ],
        daemon=True,
    )
    for _ in range(max_workers)
]
for w in workers:
    w.start()
q.join()
