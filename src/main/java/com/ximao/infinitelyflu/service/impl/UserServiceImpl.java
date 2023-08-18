package com.ximao.infinitelyflu.service.impl;

import com.ximao.infinitelyflu.dao.UserMapper;
import com.ximao.infinitelyflu.pojo.User;
import com.ximao.infinitelyflu.service.IUserService;

import java.util.List;


/**
 * 用户数据服务实现类
 * @author ximao
 * @date 2021/7/6
 */
public class UserServiceImpl implements IUserService {

    // service层调dao层，组合关系
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public User queryUserById(int id) {
        return userMapper.queryUserById(id);
    }

    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

}
