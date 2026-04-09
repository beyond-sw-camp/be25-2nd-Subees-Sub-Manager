package com.subees.submanager.recommend.mapper;

import com.subees.submanager.recommend.vo.RecommendReport;
import com.subees.submanager.recommend.vo.RecommendSubscriptionItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendMapper {

    int insertReport(RecommendReport report);

    int insertSubscriptionItem(RecommendSubscriptionItem item);

    RecommendReport findReportByIdAndUserId(@Param("reportId") Long reportId,
                                            @Param("userId") Long userId);

    List<RecommendSubscriptionItem> findItemsByReportId(@Param("reportId") Long reportId);

    int updateGeneratedResult(@Param("reportId") Long reportId,
                              @Param("reportTitle") String reportTitle,
                              @Param("generatedContent") String generatedContent,
                              @Param("totalMonthlyPrice") Integer totalMonthlyPrice,
                              @Param("reportStatus") String reportStatus);

    int saveReport(@Param("reportId") Long reportId,
                   @Param("reportTitle") String reportTitle,
                   @Param("generatedContent") String generatedContent,
                   @Param("reportStatus") String reportStatus);

    List<RecommendReport> findSavedReportsByUserId(@Param("userId") Long userId);

    int deleteItemsByReportId(@Param("reportId") Long reportId);

    int deleteReportByIdAndUserId(@Param("reportId") Long reportId,
                                  @Param("userId") Long userId);
}
