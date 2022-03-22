package com.hr.service;

import com.hr.po.User;

import java.util.List;

/**
 * @author lq
 * @date 2021-07-05 - 17:46
 */
public interface UserService {

    void add(User user);
    List<User> list();
    User selectById(int id);
    void edit(User user);
    User find();
}
