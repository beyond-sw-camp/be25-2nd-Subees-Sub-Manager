package com.subees.subscription.recommend.mapper;

import com.subees.subscription.recommend.dto.PopularSubscriptionStat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendStatsMapper {

    List<PopularSubscriptionStat> findTopPopularSubscriptions(@Param("limit") int limit);
}
