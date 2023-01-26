package com.diy.repository;

import com.diy.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByCustomerId(Long customerId);
    List<OrderEntity> findAllByStoreId(Long storeId);
}
