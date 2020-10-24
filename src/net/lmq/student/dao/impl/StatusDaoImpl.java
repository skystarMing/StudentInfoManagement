package net.lmq.student.dao.impl;

/**
 * 时间：2019.6.18
 * 功能：状态访问接口实现类
 */

import net.lmq.student.bean.Status;
import net.lmq.student.dao.StatusDao;
import net.lmq.student.dbutil.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusDaoImpl implements StatusDao{

    @Override
    public Status findById(int id) {
        Status status=null;
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select * from t_status where id=?";

        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setInt(1,id);
            ResultSet rs=pstmt.executeQuery();
            if (rs.next()){
                status=new Status();
                status.setId(rs.getInt("id"));
                status.setCollege(rs.getString("college"));
                status.setVersion(rs.getString("version"));
                status.setAuthor(rs.getString("author"));
                status.setTelephone(rs.getString("telephone"));
                status.setAddress(rs.getString("address"));
                status.setEmail(rs.getString("email"));
            }
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        return status;
    }

    @Override
    public int update(Status status) {
        int count=0;
        Connection conn=ConnectionManager.getConnection();
        String strSQL="update t_status set college=? , version=? , author=? ,"
                +"telephone=? , address=? , email=? where id=?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,status.getCollege());
            pstmt.setString(2,status.getVersion());
            pstmt.setString(3,status.getAuthor());
            pstmt.setString(4,status.getTelephone());
            pstmt.setString(5,status.getAddress());
            pstmt.setString(6,status.getEmail());
            pstmt.setInt(7,status.getId());
            count=pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }


        return count;
    }
}
