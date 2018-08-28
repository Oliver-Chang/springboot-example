package com.oliver.ElectronicCommerce.category;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "Categories")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Cacheable
    @Override
    Category getOne(Long aLong);

    @Cacheable
    @Override
    List<Category> findAll();

    @Cacheable
    @Override
    Page<Category> findAll(Pageable pageable);

    @Override
    <S extends Category> S saveAndFlush(S s);

    @Override
    void deleteById(Long aLong);
}
