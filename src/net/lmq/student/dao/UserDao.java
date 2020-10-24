package net.lmq.student.dao;

/**
 * 时间：2019.6.17
 * 功能：用户数据访问接口
 */

import java.util.List;
import net.lmq.student.bean.User;

public interface UserDao {
    int insert(User user);
    int deleteById(int id);
    int update(User user);
    User findById(int id);
    List<User> findAll();
    User login(String username, String password);
}
