package com.oliver.ElectronicCommerce.commodity;


import com.oliver.ElectronicCommerce.category.Category;
import com.oliver.ElectronicCommerce.common.result.Result;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ICommodityService {

    Result getALlByPageAndCondition(Commodity commodity, Pageable pageable);

    Result getById(Long id);

    Result addOne(Commodity commodity);

    Result updateOne(Long id, Commodity commodity);

    Result deleteOne(Long id);

}
