package net.lmq.student.service;

import net.lmq.student.bean.Student;
/**
 *时间：2019.6.19
 * 功能：学生服务接口
 */
import java.util.List;
import java.util.Vector;

public interface StudentService {
    int addStudent(Student student);
    int deleteStudentById(String id);
    int deleteStudentsByClass(String clazz);
    int deleteStudentsByDepartment(String department);
    int updateStudent(Student student);
    Student findStudentById(String id);
    List<Student> findStudentsByName(String name);
    List<Student> findStudentsByClass(String clazz);
    List<Student> findStudentsByDepartment(String department);
    List<Student> findAllStudents();
    Vector findRowsBySex();
    Vector findRowsByClass();
    Vector findRowsByDepartment();

}
