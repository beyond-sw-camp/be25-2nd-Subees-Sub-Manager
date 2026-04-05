package com.subees.subscription.payment.model.mapper;

import com.subees.subscription.payment.model.vo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CardMapper {
    int insertPaymentMethod(Payment payment);

    int countDuplicateByCustomCardCompany(@Param("userId") Long userId, @Param("customCardCompany") String customCardCompany);

    int countDuplicateByCardId(@Param("userId") Long userId, @Param("cardId") Long cardId);

    int updateCard(Payment payment);
}
