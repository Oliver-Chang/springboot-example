package com.oliver.ElectronicCommerce.commodity;


import com.oliver.ElectronicCommerce.category.Category;
import com.oliver.ElectronicCommerce.common.result.Result;
import com.oliver.ElectronicCommerce.common.result.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CommodityServiceImpl implements ICommodityService {

    @Autowired
    private CommodityRepository repository;

    @Override
    public Result getALlByPageAndCondition(Commodity commodity, Pageable pageable) {
        if (pageable == null || commodity == null)
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true)
                .withIgnorePaths("id", "image")
                .withIgnoreNullValues();
        Example<Commodity> example = Example.of(commodity, matcher);
        Page<Commodity> all = repository.findAll(example, pageable);
        return Result.success(all);
    }

    @Override
    public Result getById(Long id) {
        if (id == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Commodity one = repository.getOne(id);
        if (one == null)
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        return Result.success(one);
    }

    @Override
    public Result addOne(Commodity commodity) {
        if (commodity == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Commodity commodity1 = repository.saveAndFlush(commodity);
        if (commodity1 == null) {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success(commodity1);
    }

    @Override
    public Result updateOne(Long id, Commodity commodity) {
        if (commodity == null || id == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Commodity one = repository.getOne(id);
        if (one == null) {
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        }
        commodity.setId(id);
        Commodity commodity1 = repository.saveAndFlush(commodity);
        if (commodity1 == null) {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success(commodity1);
    }

    @Override
    public Result deleteOne(Long id) {
        if (id == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Commodity one = repository.getOne(id);
        if (one == null) {
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        }
        repository.deleteById(id);
        return Result.success();
    }
}
