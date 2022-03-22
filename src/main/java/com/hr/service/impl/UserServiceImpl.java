package com.hr.service.impl;

import com.hr.mapper.UserMapper;
import com.hr.po.User;
import com.hr.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lq
 * @date 2021-07-06 - 14:34
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public User selectById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public void edit(User user) {
        userMapper.update(user);
    }

    @Override
    public User find() {
        return userMapper.find();
    }
}
