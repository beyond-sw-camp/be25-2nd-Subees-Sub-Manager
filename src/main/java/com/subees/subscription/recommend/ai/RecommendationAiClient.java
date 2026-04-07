package com.subees.subscription.recommend.ai;

import com.subees.subscription.recommend.entity.RecommendReport;
import com.subees.subscription.recommend.entity.RecommendSubscriptionItem;

import java.util.List;

public interface RecommendationAiClient {

    GeneratedRecommendation generate(RecommendReport report,
                                     List<RecommendSubscriptionItem> subscriptionItems,
                                     String statsSummary);
}
