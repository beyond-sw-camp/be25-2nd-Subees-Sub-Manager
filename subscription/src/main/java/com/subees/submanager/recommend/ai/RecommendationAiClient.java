package com.subees.submanager.recommend.ai;

import com.subees.submanager.recommend.vo.RecommendReport;
import com.subees.submanager.recommend.vo.RecommendSubscriptionItem;

import java.util.List;

public interface RecommendationAiClient {

    GeneratedRecommendation generate(RecommendReport report,
                                     List<RecommendSubscriptionItem> subscriptionItems,
                                     String statsSummary);
}
