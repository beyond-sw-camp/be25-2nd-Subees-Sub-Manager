package com.subees.subscription.payment.model.mapper;

import com.subees.subscription.payment.model.dto.CardCreateRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CardMapper {
    int insertPaymentMethod(CardCreateRequestDto cardCreateRequestDto);

    int countDuplicateByCustomCardCompany(@Param("userId") Long userId, @Param("customCardCompany") String customCardCompany);

    int countDuplicateByCardId(@Param("userId") Long userId, @Param("cardId") Long cardId);
}
