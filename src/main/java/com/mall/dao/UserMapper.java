package com.mall.dao;

import com.mall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUserName(String userName);

    User selectLogin(@Param("username") String userName, @Param("password") String password);

    int checkUserMail(String email);

    String selectQuestionByUsername(String userName);

    int checkAnswer(@Param("username") String userName, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByUserName(@Param("password") String password, @Param("username")String username);

    int checkPassword(@Param("password") String password, @Param("userid") Integer userId);

    int checkEmailByUserId(@Param("email") String email, @Param("userid") Integer userId);
}