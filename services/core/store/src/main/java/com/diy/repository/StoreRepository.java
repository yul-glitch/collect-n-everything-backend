package com.diy.repository;

import com.diy.entity.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>, JpaSpecificationExecutor<StoreEntity> {
    Optional<StoreEntity> findByStoreName(String name);
//    @Query(name =
//            "select * from store inner join address on address.store_id = store.store_id where store.deleted_at is null and address.deleted at is null",
//            nativeQuery = true)
//    List<StoreEntity> findAllStores();

}

