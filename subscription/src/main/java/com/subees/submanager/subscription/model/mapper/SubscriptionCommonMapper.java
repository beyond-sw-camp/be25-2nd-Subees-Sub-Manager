package com.subees.submanager.subscription.model.mapper;

import com.subees.submanager.subscription.model.dto.common.CategoryResponse;
import com.subees.submanager.subscription.model.dto.common.ItemResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

;

@Mapper
public interface SubscriptionCommonMapper {

    List<CategoryResponse> selectAllCategories();

    List<ItemResponse> selectItemsByCategoryId(@Param("categoryId") Long categoryId);
}