package com.oliver.ElectronicCommerce.user;


import com.oliver.ElectronicCommerce.common.result.Result;

import com.oliver.ElectronicCommerce.order.Order;
import org.aspectj.weaver.ast.Or;
import org.jboss.logging.FormatWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping("")
    public Result getAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Result getUser(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PostMapping("")
    public Result addOne(@RequestBody User user) {
        return service.addOne(user);
    }

    @GetMapping("/{userId}/order")
//    @PreAuthorize("@guard.checkUserId(authentication,#userId)")
    public Result getUserOrder(
            @PageableDefault(value = 10, sort = {"amount"}, direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable Long userId,
            @ModelAttribute Order order) {
        return service.getUserOrder(userId, order, pageable);
    }

    @PostMapping("/{userId}/order")
    public Result addUserOrder(@PathVariable Long userId, @RequestBody Order order) {
        return service.addUserOrder(userId, order);
    }

    @PutMapping("/{userId}/order/{orderId}")
    public Result updateUserOrder(@PathVariable Long userId, @PathVariable Long orderId, @RequestBody Order order) {
        return service.updateUserOrder(userId, orderId, order);
    }

    @DeleteMapping("/{userId}/order/{orderId}")
    public Result deleteUserOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        return service.deleteUserOrder(userId, orderId);
    }
}
