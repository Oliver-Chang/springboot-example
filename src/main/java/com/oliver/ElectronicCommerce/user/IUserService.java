package com.oliver.ElectronicCommerce.user;

import com.oliver.ElectronicCommerce.common.result.Result;
import com.oliver.ElectronicCommerce.order.Order;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Result getAll(User user, Pageable pageable);

    Result getOne(Long id);

    Result getUserOrder(Long userId, Order order, Pageable pageable);

    Result addUserOrder(Long userId, Order order);

    Result updateUserOrder(Long userId, Long orderId, Order order);

    Result deleteUserOrder(Long userId, Long orderId);

    Result addOne(User user);

    Result updateOne(Long id, User user);

    Result deleteOne(Long id);
}
