package com.oliver.ElectronicCommerce.order;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@CacheConfig(cacheNames = "Orders")
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    @Cacheable
    Order getOne(Long aLong);

    @Override
    @Cacheable
    Page<Order> findAll(Pageable pageable);

    @Cacheable
    Page<Order> findAll(Example example, Pageable pageable);

    @Override
    <S extends Order> S saveAndFlush(S s);

    @Override
    void deleteById(Long aLong);

}
