package com.oliver.ElectronicCommerce.order;

import com.oliver.ElectronicCommerce.user.User;
import com.oliver.ElectronicCommerce.user.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(value = false)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void findAll() {
        List<Order> all = repository.findAll();
        for (Order order : all) {
        }
    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll1() {
    }

    @Test
    @Transactional
    public void saveAndFlush() {
        Order build = new Order();
        build.setLogistics("1231");
        Order order = repository.saveAndFlush(build);
        User user = new User();
        user.setAddress("123");
        user.setMail("asd");
        User user2 = userRepository.saveAndFlush(user);
        user2.addOrder(order);
        userRepository.saveAndFlush(user2);


    }

    @Test
    public void deleteById() {
    }
}
