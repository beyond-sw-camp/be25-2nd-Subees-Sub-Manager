package com.subees.subscription.user.mapper;

import com.subees.subscription.user.entity.User;
import com.subees.subscription.user.entity.UserState;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int countByEmail(@Param("email") String email);

    int countByNickname(@Param("nickname") String nickname);

    User findByEmail(@Param("email") String email);

    int insertUser(User user);

    User findById(@Param("userId") Long userId);

    int updateNickname(@Param("userId") Long userId,
                       @Param("nickname") String nickname);

    int updatePassword(@Param("userId") Long userId,
                       @Param("password") String password);

    int updateUserState(@Param("userId") Long userId,
                        @Param("userState") UserState userState);

    int updateProfileImageUrl(@Param("userId") Long userId,
                              @Param("profileImageUrl") String profileImageUrl);
}