package com.oliver.ElectronicCommerce.common.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {


    @Override
    long count();

    @Override
    List<T> findAll();

    @Override
    Page<T> findAll(Pageable pageable);

    Page<List<T>> findAll(Sort sort, Pageable pageable);

    @Override
    Optional<T> findById(ID id);

    @Override
    <S extends T> S saveAndFlush(S s);

    @Override
    <S extends T> List<S> saveAll(Iterable<S> iterable);

    @Override
    void deleteById(ID id);

    @Override
    void flush();

}