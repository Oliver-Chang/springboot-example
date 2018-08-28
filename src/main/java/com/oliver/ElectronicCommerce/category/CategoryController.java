package com.oliver.ElectronicCommerce.category;


import com.oliver.ElectronicCommerce.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl service;

    @GetMapping("")
    public Result getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public Result addOne(@RequestBody Category category) {
        return service.addOne(category);
    }

    @PutMapping("/{id}")
    public Result updateOne(@PathVariable Long id, @RequestBody Category category) {
        return service.updateOne(id, category);
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
