package com.mall.service;

import com.mall.common.ServerResponse;
import com.mall.pojo.Category;

import java.util.List;

public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse<List<Category>> getChildParallelCategory(Integer categoryId);

    ServerResponse updateCategory(String categoryName, Integer categoryId);

    ServerResponse selectCategoryAndDeepChildrenCategory(Integer categoryId);
}
