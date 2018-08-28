package com.oliver.ElectronicCommerce.commodity;


import com.oliver.ElectronicCommerce.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private ICommodityService service;

    @GetMapping("")
    public Result getAllByPageAndCondition(
            @PageableDefault(value = 10, sort = {"price"}, direction = Sort.Direction.ASC) Pageable pageable,
            @ModelAttribute Commodity commodity) {
        return service.getALlByPageAndCondition(commodity, pageable);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public Result addOne(@RequestBody Commodity commodity) {
        return service.addOne(commodity);
    }

    @PutMapping("/{id}")
    public Result updateOne(@PathVariable Long id, @RequestBody Commodity commodity) {
        return service.updateOne(id, commodity);
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Long id) {
        return service.deleteOne(id);
    }
}
