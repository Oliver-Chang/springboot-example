package com.oliver.ElectronicCommerce.order;


import com.oliver.ElectronicCommerce.common.result.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface IOrderService {
    Result getById(Long id);

    Result getAllByPageAndCondition(Order order, Pageable pageable);

    Result getUserOrder(Long userId, Order order, Pageable pageable);

    Result addOne(Order order);

    Result addUserOrder(Long userId, Order order);

    Result updateOne(Long id, Order order);

    Result deleteOne(Long id);
}
