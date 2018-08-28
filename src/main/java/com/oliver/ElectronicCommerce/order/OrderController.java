package com.oliver.ElectronicCommerce.order;


import com.oliver.ElectronicCommerce.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/order")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class OrderController {

    @Autowired
    private OrderServiceImpl service;

    @GetMapping("")
    public Result getAllByPageAndCondition(
            @PageableDefault(value = 10, sort = {"amount"}, direction = Sort.Direction.ASC) Pageable pageable,
            @ModelAttribute Order order, Principal loginUser) {
        System.out.println(loginUser.toString());
        System.out.println("username principal");
        System.out.println(loginUser.getName());
        return service.getAllByPageAndCondition(order, pageable);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @PostMapping("")
    public Result addOne(@RequestBody Order order) {
        return service.addOne(order);
    }

    @PutMapping("/{id}")
    public Result updateOne(@PathVariable Long id, @RequestBody Order order) {
        return service.updateOne(id, order);
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}
