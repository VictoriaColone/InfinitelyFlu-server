package com.ximao.infinitelyflu.dao;

import com.ximao.infinitelyflu.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作用户类
 * @author ximao
 * @date 2021/7/6
 */
public interface UserMapper {

    // 对用户CURD
    int addUser(User user);

    int deleteUserById(@Param("id") int id);

    int updateUser(User user);

    User queryUserById(@Param("id") int id);

    List<User> queryAllUser();

}
