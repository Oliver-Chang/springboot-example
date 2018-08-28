package com.oliver.ElectronicCommerce.user;

import com.oliver.ElectronicCommerce.common.result.Result;
import com.oliver.ElectronicCommerce.common.result.ResultCode;
import com.oliver.ElectronicCommerce.order.Order;
import com.oliver.ElectronicCommerce.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Result getAll(User user, Pageable pageable) {
        return null;
    }

    @Override
    public Result getOne(Long id) {
        if (id == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        User one = userRepository.getOne(id);
        if (one == null) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        return Result.success(one);
    }

    @Override
    public Result getUserOrder(Long userId, Order order, Pageable pageable) {
        if (userId == null || order == null || pageable == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true)
                .withIgnoreNullValues();
        order.setUserId(userId);
        Example<Order> example = Example.of(order, matcher);
        Page<Order> all = orderRepository.findAll(example, pageable);
        return Result.success(all);
    }

    @Override
    public Result addUserOrder(Long userId, Order order) {
        if (userId == null || order == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        User one = userRepository.getOne(userId);
        if (one == null) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        order.setUserId(one.getId());
        order.setSerialNumber(UUID.randomUUID().toString());
        Order order1 = orderRepository.saveAndFlush(order);
        return Result.success(order1);
    }

    @Override
    public Result updateUserOrder(Long userId, Long orderId, Order order) {
        if (userId == null || orderId == null || order == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        User one = userRepository.getOne(userId);
        if (one == null) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        Order one1 = orderRepository.getOne(orderId);
        if (one1 == null) {
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }

        if (one1.getUserId() == one.getId()) {
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        }
        Order order1 = orderRepository.saveAndFlush(order);
        return Result.success(order1);
    }

    @Override
    public Result deleteUserOrder(Long userId, Long orderId) {
        if (userId == null || orderId == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        User one = userRepository.getOne(userId);
        if (one == null) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        Order one1 = orderRepository.getOne(orderId);
        if (one1 == null) {
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }
        if (one1.getUserId() == one.getId()) {
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        }
        orderRepository.deleteById(orderId);
        return Result.success(one1);
    }

    @Override
    public Result addOne(User user) {
        if (user == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Date now = new Date(System.currentTimeMillis());
        user.setRegDate(now);
        user.setLastRestPassword(now);
        User user1 = userRepository.saveAndFlush(user);
        return Result.success(user1);
    }

    @Override
    public Result updateOne(Long id, User user) {
        if (id == null || user == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        User one = userRepository.getOne(id);
        if (one == null) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        Date now = new Date(System.currentTimeMillis());
        if (!one.getPassword().equals(user.getPassword())) {
            user.setLastRestPassword(now);
        } else {
            user.setLastRestPassword(one.getLastRestPassword());
        }
        user.setId(one.getId());
        user.setRegDate(one.getRegDate());
        User user1 = userRepository.saveAndFlush(user);
        return Result.success(user1);
    }

    @Override
    public Result deleteOne(Long id) {
        if (id == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        User one = userRepository.getOne(id);
        if (one == null) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        userRepository.deleteById(id);
        return Result.success();
    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsernameOrTelephoneOrMail(s, s, s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return user;
    }
}
