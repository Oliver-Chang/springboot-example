package com.oliver.ElectronicCommerce.category;


import com.oliver.ElectronicCommerce.common.result.Result;
import com.oliver.ElectronicCommerce.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Result getAll() {
        List<Category> all = repository.findAll();
        return Result.success(all);
    }

    @Override
    public Result getById(Long id) {
        if (id == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Category one = repository.getOne(id);
        return Result.success(one);
    }

    @Override
    public Result addOne(Category category) {
        if (category == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Category category1 = repository.saveAndFlush(category);
        if (category1 == null) {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success(category1);
    }

    @Override
    public Result updateOne(Long id, Category category) {
        if (id == null || category == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Category one = repository.getOne(id);
        if (one == null) {
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        }
        category.setId(id);
        Category category1 = repository.saveAndFlush(category);
        if (category1 == null) {
            return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success(category1);
    }

    @Override
    public Result deleteById(Long id) {
        if (id == null) {
            return Result.failure(ResultCode.PARAM_IS_BLANK);
        }
        Category one = repository.getOne(id);
        if (one == null) {
            return Result.failure(ResultCode.DATA_NOT_EXIST);
        }
        repository.delete(one);
        return Result.success();
    }
}
