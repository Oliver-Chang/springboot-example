package com.oliver.ElectronicCommerce.commodity;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@CacheConfig(cacheNames = "Commodities")
public interface CommodityRepository extends JpaRepository<Commodity, Long> {

    @Override
    @Cacheable
    Commodity getOne(Long aLong);

    @Override
    @Cacheable
    List<Commodity> findAll();

    @Override
    @Cacheable
    Page<Commodity> findAll(Pageable pageable);

    @Cacheable
    Page<Commodity> findAll(Example example, Pageable pageable);

    @Override
    <S extends Commodity> S saveAndFlush(S s);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
