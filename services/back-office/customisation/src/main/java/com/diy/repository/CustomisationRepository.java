package com.diy.repository;

import com.diy.entity.CustomisationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomisationRepository extends JpaRepository<CustomisationEntity, Long> {
}
