package net.lmq.student.dao.impl;

/**
 * 功能：学校数据访问接口实现类
 */

import net.lmq.student.bean.College;
import net.lmq.student.dao.CollegeDao;
import net.lmq.student.dbutil.ConnectionManager;

import java.sql.*;

public class CollegeDaoImpl implements CollegeDao {
    @Override
    public College findById(int id) {
        //声明学校对象
        College college=null;

        //1.获取数据库连接
        Connection conn= ConnectionManager.getConnection();
        //2.定义SQL字符串
        String strSQL="select * from t_college where id=?";
        try {
            //3.创建预备语句对象
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setInt(1,id);
            //5.执行
            ResultSet rs=pstmt.executeQuery();
            //6.判断结果集是否有记录
            if (rs.next()){
                //实例化学校对象
                college=new College();
                //获取当前记录字段值去设置学校对象的属性
                college.setId(rs.getInt("id"));
                college.setName(rs.getString("name"));
                college.setPresident(rs.getString("president"));
                college.setStartTime(rs.getDate("start_time"));
                college.setTelephone(rs.getString("telephone"));
                college.setEmail(rs.getString("email"));
                college.setAddress(rs.getString("address"));
                college.setProfile(rs.getString("profile"));
            }
            //7.关闭预备语句对象
            pstmt.close();
            //8.关闭结果集
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭数据库
            ConnectionManager.closeConnection(conn);
        }

        //返回学校对象
        return college;
    }

    @Override
    public int update(College college) {
        //定义更新记录数
        int count=0;

        //1.获取数据库连接
        Connection conn=ConnectionManager.getConnection();
        //2.定义SQL字符串
        try {
            String strSQL="update t_college set name = ?,president = ?,start_time = ? ,"
                    +"telephone=? ,email=? , profile=? where id =?";
            //3.创建预备语句
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            //4.设置占位符
            pstmt.setString(1,college.getName());
            pstmt.setString(2,college.getPresident());
            pstmt.setTimestamp(3,new Timestamp(college.getStartTime().getTime()));
            pstmt.setString(4,college.getTelephone());
            pstmt.setString(5,college.getEmail());
            pstmt.setString(6,college.getProfile());
            pstmt.setInt(7,college.getId());

            //5.执行结果，返回更新记录数
            count=pstmt.executeUpdate();
            //6.关闭预备语句
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭数据库
            ConnectionManager.closeConnection(conn);
        }
        //返回更新记录数
        return count;
    }
}
