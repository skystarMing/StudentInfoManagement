package net.lmq.student.service.impl;

import net.lmq.student.bean.User;
import net.lmq.student.dao.UserDao;
import net.lmq.student.dao.impl.UserDaoImpl;
import net.lmq.student.service.UserService;

import java.util.List;

/**
 * 功能：用户服务接口实现类
 * 时间：2019.6.19
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public int addUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public User findUserById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }
}
