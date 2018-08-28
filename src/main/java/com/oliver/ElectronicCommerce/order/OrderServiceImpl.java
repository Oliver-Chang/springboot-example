package com.oliver.ElectronicCommerce.order;


import com.oliver.ElectronicCommerce.common.result.Result;
import com.oliver.ElectronicCommerce.common.result.ResultCode;

import com.oliver.ElectronicCommerce.user.User;
import com.oliver.ElectronicCommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Result getById(Long id) {
        if (id == null) {
            Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Order one = repository.getOne(id);
        return Result.success(one);
    }

    @Override
    public Result getAllByPageAndCondition(Order order, Pageable pageable) {
        if (order == null || pageable == null) {
            Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true)
                .withIgnoreNullValues();
        Example<Order> example = Example.of(order, matcher);
        Page<Order> all = repository.findAll(example, pageable);
        return Result.success(all);
    }

    @Override
    public Result getUserOrder(Long userId, Order order, Pageable pageable) {
        if (userId == null || order == null || pageable == null) {
            Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        User one = userRepository.getOne(userId);
        if (one == null) {
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        }
        order.setUserId(one.getId());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true)
                .withIgnorePaths("id")
                .withIgnoreNullValues();
        Example<Order> example = Example.of(order, matcher);
        Page<Order> all = repository.findAll(example, pageable);
        return Result.success(all);
    }

    @Override
    public Result addOne(Order order) {
        if (order == null) {
            Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Order order1 = repository.saveAndFlush(order);
        if (order1 == null) {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success(order1);
    }

    @Override
    public Result addUserOrder(Long userId, Order order) {
        if (userId == null || order == null) {
            Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        User one = userRepository.getOne(userId);
        if (one == null) {
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        }
        order.setUserId(one.getId());
        Order order1 = repository.saveAndFlush(order);
        if (order1 == null) {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success(order1);
    }

    @Override
    public Result updateOne(Long id, Order order) {
        if (id == null || order == null) {
            Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Order one = repository.getOne(id);
        if (one == null) {
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        }
        order.setId(id);
        Order order1 = repository.saveAndFlush(order);
        if (order1 == null) {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success(order1);
    }

    @Override
    public Result deleteOne(Long id) {
        if (id == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Order one = repository.getOne(id);
        if (one == null) {
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        }
        repository.deleteById(id);
        return Result.success();
    }
}
