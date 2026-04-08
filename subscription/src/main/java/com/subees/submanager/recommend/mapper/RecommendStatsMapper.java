package com.subees.submanager.recommend.mapper;

import com.subees.submanager.recommend.dto.PopularSubscriptionStat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendStatsMapper {

    List<PopularSubscriptionStat> findTopPopularSubscriptions(@Param("limit") int limit);
}
