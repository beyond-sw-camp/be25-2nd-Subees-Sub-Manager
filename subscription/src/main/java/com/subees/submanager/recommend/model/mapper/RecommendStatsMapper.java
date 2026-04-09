package com.subees.submanager.recommend.model.mapper;

import com.subees.submanager.recommend.model.dto.PopularSubscriptionStat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendStatsMapper {

    List<PopularSubscriptionStat> findTopPopularSubscriptions(@Param("limit") int limit);
}
