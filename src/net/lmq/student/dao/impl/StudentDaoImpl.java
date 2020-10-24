package net.lmq.student.dao.impl;

import net.lmq.student.bean.Student;
import net.lmq.student.dao.StudentDao;
import net.lmq.student.dbutil.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 功能：学生访问数据接口实现类
 * 时间：2019.6.18
 */

public class StudentDaoImpl implements StudentDao {
    @Override//插入学生记录
    public int insert(Student student) {
        //定义插入记录数
        int count=0;
        //1.获得数据库连接
        Connection conn= ConnectionManager.getConnection();
        //2.定义SQL字符串
        String strSQL="insert into t_student (id,name, sex ,age,department,class,telephone)"
                +" values(?,?,?,?,?,?,?)";
        try {
            //3.创建预备语句对象
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            //4.设置站位符
            pstmt.setString(1,student.getId());
            pstmt.setString(2,student.getName());
            pstmt.setString(3,student.getSex());
            pstmt.setInt(4,student.getAge());
            pstmt.setString(5,student.getDepartment());
            pstmt.setString(6,student.getClazz());
            pstmt.setString(7,student.getTelephone());
            //5.执行SQL语句，返回记录数
            count=pstmt.executeUpdate();
            //6.关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        //返回插入记录数
        return count;
    }

    @Override
    public int deleteById(String id) {
        int count=0;

        Connection conn=ConnectionManager.getConnection();
        String strSQL="delete from t_student where id=? ";

        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,id);
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
    public int deleteByClass(String clazz) {
        int count=0;
        Connection conn=ConnectionManager.getConnection();
        String strSQL="delete from t_student where class= ?";
//
        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,clazz);
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
    public int deleteByDepartment(String department) {
        int count=0;
        Connection conn=ConnectionManager.getConnection();
        String strSQL="delete from t_student where department= ?";

        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,department);
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
    public int update(Student student) {
        int count=0;
        Connection conn=ConnectionManager.getConnection();
        String strSQL="update t_student set name= ? , sex= ? , age= ? , department= ? , class= ? , "
                +"telephone= ? where id=?";
        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,student.getName());
            pstmt.setString(2,student.getSex());
            pstmt.setInt(3,student.getAge());
            pstmt.setString(4,student.getDepartment());
            pstmt.setString(5,student.getClazz());
            pstmt.setString(6,student.getTelephone());
            pstmt.setString(7,student.getId());
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
    public Student findById(String id) {
       //声明学生对象
        Student student=null;
        //链接，字符串
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select * from t_student where id= ?";
        try {
            //创建预备语句对象
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,id);
            //执行
            ResultSet rs=pstmt.executeQuery();
            //判断结果集是否有对象
            if (rs.next()){
                //实例化student对象
                student=new Student();
                //获取学生shuxing
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setAge(rs.getInt("age"));
                student.setDepartment(rs.getString("department"));
                student.setClazz(rs.getString("class"));
                student.setTelephone(rs.getString("telephone"));
            }
            //关闭
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return student;
    }

    @Override
    public Student findByName(String name) {
        //声明学生对象
        Student student=null;
        //链接，字符串
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select * from t_student where name= ?";
        try {
            //创建预备语句对象
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,name);
            //执行
            ResultSet rs=pstmt.executeQuery();
            //判断结果集是否有对象
            if (rs.next()){
                //实例化student对象
                student=new Student();
                //获取学生shuxing
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setAge(rs.getInt("age"));
                student.setDepartment(rs.getString("department"));
                student.setClazz(rs.getString("class"));
                student.setTelephone(rs.getString("telephone"));
            }
            //关闭
            pstmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return student;
    }

    @Override
    public List<Student> findByNname(String name) {
        // 声明学生列表
        List<Student> students = new ArrayList<Student>();

        // 1. 获取数据库连接对象
        Connection conn = ConnectionManager.getConnection();
        // 2. 定义SQL字符串
        String strSQL = "select * from t_student where name like ?";
        try {
            // 3. 创建预备语句对象
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 4. 设置占位符的值
            pstmt.setString(1, name + "%");
            // 5. 执行SQL，返回结果集
            ResultSet rs = pstmt.executeQuery();
            // 6. 遍历结果集
            while (rs.next()) {
                // 创建学生实体
                Student student = new Student();
                // 利用当前记录各字段值设置学生实体属性
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setAge(rs.getInt("age"));
                student.setDepartment(rs.getString("department"));
                student.setClazz(rs.getString("class"));
                student.setTelephone(rs.getString("telephone"));
                // 将实体添加到学生列表
                students.add(student);
            }
            // 7. 关闭结果集
            rs.close();
            // 8. 关闭预备语句对象
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接
            ConnectionManager.closeConnection(conn);
        }

        // 返回学生列表
        return  students;
    }


    @Override
    public List<Student> findByClass(String clazz) {
        List<Student> students=new ArrayList<Student>();
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select * from t_student where class like ?";

        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,clazz+"%");
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Student student=new Student();
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setAge(rs.getInt("age"));
                student.setDepartment(rs.getString("department"));
                student.setClazz(rs.getString("class"));
                student.setTelephone(rs.getString("telephone"));
                students.add(student);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        return students;
    }

    @Override
    public List<Student> findByDepartment(String department) {

        List<Student> students=new ArrayList<Student>();
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select * from t_student where department =?";

        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            pstmt.setString(1,department);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Student student=new Student();
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setAge(rs.getInt("age"));
                student.setDepartment(rs.getString("department"));
                student.setClazz(rs.getString("class"));
                student.setTelephone(rs.getString("telephone"));
                students.add(student);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        return students;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students=new ArrayList<Student>();
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select * from t_student ";

        try {
            PreparedStatement pstmt=conn.prepareStatement(strSQL);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Student student=new Student();
                student.setId(rs.getString("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getString("sex"));
                student.setAge(rs.getInt("age"));
                student.setDepartment(rs.getString("department"));
                student.setClazz(rs.getString("class"));
                student.setTelephone(rs.getString("telephone"));
                students.add(student);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        return students;
    }

    @Override
    public Vector findRowsBySex() {
        //定义行变量
        Vector rows=new Vector();
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select sex as '性别', count(*) as '人数'"
                + " from t_student group by sex order by sex desc";
        try {
            //创建语句对象
            Statement stmt=conn.createStatement();
            //执行
            ResultSet rs=stmt.executeQuery(strSQL);
            while (rs.next()){
                //定义当前行向量
                Vector<String> currentRow=new Vector();
                //利用当前记录字段设置当前行向量的元素值
                currentRow.addElement(rs.getString("性别"));
                currentRow.addElement(rs.getInt("人数") + "");
                rows.addElement(currentRow);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }


        return rows;
    }

    @Override
    public Vector findRowsByClass() {
        Vector rows=new Vector();
        Connection conn=ConnectionManager.getConnection();
        String strSQL= "select class as '班级', count(*) as '人数'"
                + " from t_student group by class order by class desc";
        try {
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(strSQL);
            while (rs.next()){
                Vector<String> currentRow =new Vector();
                currentRow.addElement(rs.getString("班级"));
                currentRow.addElement(rs.getInt("人数")+" ");
                rows.addElement(currentRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }

        return rows;
    }

    @Override
    public Vector findRowsByDepartment() {
        Vector rows=new Vector();
        Connection conn=ConnectionManager.getConnection();
        String strSQL="select department as '系部', count(*) as '人数'"
                + " from t_student group by department order by department desc";
        try {
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(strSQL);
            while (rs.next()){
                Vector<String> currentROW=new Vector();
                currentROW.addElement(rs.getString("系部"));
                currentROW.addElement(rs.getInt("人数")+" ");
                rows.addElement(currentROW);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return rows;
    }
}
