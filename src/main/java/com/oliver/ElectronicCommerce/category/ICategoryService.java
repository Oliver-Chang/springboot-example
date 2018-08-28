package com.oliver.ElectronicCommerce.category;


import com.oliver.ElectronicCommerce.commodity.Commodity;
import com.oliver.ElectronicCommerce.common.result.Result;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {

    Result getAll();

    Result getById(Long id);

    Result addOne(Category category);

    Result updateOne(Long id, Category category);

    Result deleteById(Long id);
}
