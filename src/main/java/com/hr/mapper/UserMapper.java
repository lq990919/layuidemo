package com.hr.mapper;

import com.hr.po.User;

import java.util.List;

/**
 * @author lq
 * @date 2021-07-05 - 17:43
 */
public interface UserMapper {

    void insert(User user);
    User findById(int id);
    List<User> list();
    void update(User user);
    User find();

}
