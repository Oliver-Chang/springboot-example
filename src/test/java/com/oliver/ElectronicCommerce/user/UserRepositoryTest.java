package com.oliver.ElectronicCommerce.user;

import com.oliver.ElectronicCommerce.order.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void creatUser() {

    }

    @Test
    public void getUser() {
        List<User> all = repository.findAll();
        for (User user: all) {
            System.out.println("user id ");
            System.out.println(user.getId());
        }
    }
}