package net.lmq.student.dao;

/**
 * 时间：2019.6.17
 * 功能：学生数据访问接口
 */

import net.lmq.student.bean.Student;
/**
 * 功能：学生接口
 * 时间：2019.6.18
 */
import java.util.List;
import java.util.Vector;

public interface StudentDao {
    int insert(Student student);
    int deleteById(String id);
    int deleteByClass(String clazz);
    int deleteByDepartment(String department);
    int update(Student student);
    Student findById(String id);
    Student findByName(String name);

    List<Student>  findByNname(String name);

    List<Student> findByClass(String clazz);
    List<Student> findByDepartment(String department);
    List<Student> findAll();
    Vector findRowsBySex();
    Vector findRowsByClass();
    Vector findRowsByDepartment();

}
