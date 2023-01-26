package com.diy.repository;

import com.diy.entity.AddressEntity;
import com.diy.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findByStoreAndDeletedAt(StoreEntity store, LocalDateTime deletedAt);
}
