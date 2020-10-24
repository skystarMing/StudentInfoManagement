package net.lmq.student.dao.impl;

import net.lmq.student.bean.User;
import net.lmq.student.dao.UserDao;
import net.lmq.student.dbutil.ConnectionManager;
/**
 * 功能：用户数据访问接口实现类
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public int insert(User user) {
        int count=0;
        Connection conn=ConnectionManager.getConnection();
        String strSQL="insert into t_user (username, password, telephone, register_time)"
                + " values (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            pstmt.setString(3,user.getTelephone());
            pstmt.setTimestamp(4,new Timestamp(user.getRegisterTime().getTime()));
            count=pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        return count;
    }

    @Override
    public int deleteById(int id) {
        // 定义删除记录数
        int count = 0;

        // 1. 获取数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 定义SQL字符串
        String strSQL = "delete from t_user where id = ?";
        try {
            // 3. 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 4. 设置占位符的值
            pstmt.setInt(1, id);
            // 5. 执行SQL，返回删除记录数
            count = pstmt.executeUpdate();
            // 6. 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接
            ConnectionManager.closeConnection(conn);
        }

        // 返回删除记录数
        return count;

    }

    @Override
    public int update(User user) {
        int count=0;
        Connection conn=ConnectionManager.getConnection();
        String strSQL="update t_user set username = ?, password = ?, telephone = ?,"
                + " register_time = ? where id = ?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getTelephone());
            pstmt.setTimestamp(4, new Timestamp(user.getRegisterTime().getTime()));
            pstmt.setInt(5,user.getId());
            count=pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return count;
    }

    @Override
    public User findById(int id) {
        User user=null;
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select * from t_user where id= ?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setInt(1,id);
            ResultSet rs=pstmt.executeQuery();
            if (rs.next()){
                user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setTelephone(rs.getString("telephone"));
                user.setRegisterTime(rs.getTimestamp("register_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users=new ArrayList<User>();
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select * from t_user";
        try {
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(strSQL);
            while (rs.next()){
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setTelephone(rs.getString("telephone"));
                user.setRegisterTime(rs.getTimestamp("register_time"));
                users.add(user);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        return users;
    }

    @Override
    public User login(String username, String password) {
        User user=null;
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select * from t_user where username= ? and password= ?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            ResultSet rs=pstmt.executeQuery();
            if (rs.next()){
                user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setTelephone(rs.getString("telephone"));
                user.setRegisterTime(rs.getTimestamp("register_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);

        }

        return user;
    }
}
