package com.ximao.infinitelyflu.service;

import com.ximao.infinitelyflu.pojo.User;

import java.util.List;


/**
 * 用户数据服务接口
 * @author ximao
 * @date 2021/7/6
 */
public interface IUserService {

    // 对用户CURD
    int addUser(User user);

    int deleteUserById(int id);

    int updateUser(User user);

    User queryUserById(int id);

    List<User> queryAllUser();

}
