package com.oliver.ElectronicCommerce.user;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    @Cacheable
    Page<User> findAll(Pageable pageable);

    @Override
    @Cacheable
    List<User> findAll();

    @Override
    @Cacheable
    User getOne(Long aLong);

    @Override
    <S extends User> S saveAndFlush(S s);


    @Override
    void deleteById(Long aLong);

    @Cacheable
    User findUserByUsernameOrTelephoneOrMail(String username, String telephone, String mail);
}