package com.oliver.ElectronicCommerce.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Override
    Optional<Admin> findById(Long aLong);

    @Override
    <S extends Admin> S saveAndFlush(S s);

    @Override
    void deleteById(Long aLong);
}
