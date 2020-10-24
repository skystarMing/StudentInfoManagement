package net.lmq.student.service;

import net.lmq.student.bean.User;

import java.util.List;

/**
 * 时间：2019.6.19
 * 功能：用户服务接口
 */
public interface UserService {
    int addUser(User user);
    int deleteUserById(int id);
    int updateUser(User user);
    User findUserById(int id);
    List<User> findAllUsers();
    User login(String username, String password);

}
