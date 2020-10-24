package net.lmq.student.service.impl;
/**
 * 功能：学生服务接口实现类
 */

import net.lmq.student.bean.Student;
import net.lmq.student.dao.StudentDao;
import net.lmq.student.dao.impl.StudentDaoImpl;
import net.lmq.student.service.StudentService;

import java.util.List;
import java.util.Vector;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao=new StudentDaoImpl();
    @Override
    public int addStudent(Student student) {
        return studentDao.insert(student);
    }

    @Override
    public int deleteStudentById(String id) {
        return studentDao.deleteById(id);
    }

    @Override
    public int deleteStudentsByClass(String clazz) {
        return studentDao.deleteByClass(clazz);
    }

    @Override
    public int deleteStudentsByDepartment(String department) {
        return studentDao.deleteByDepartment(department);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.update(student);
    }

    @Override
    public Student findStudentById(String id) {
        return studentDao.findById(id);
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return studentDao.findByNname(name);
    }

    @Override
    public List<Student> findStudentsByClass(String clazz) {
        return studentDao.findByClass(clazz);
    }

    @Override
    public List<Student> findStudentsByDepartment(String department) {
        return studentDao.findByDepartment(department);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public Vector findRowsBySex() {
        return studentDao.findRowsBySex();
    }

    @Override
    public Vector findRowsByClass() {
        return studentDao.findRowsByClass();
    }

    @Override
    public Vector findRowsByDepartment() {
        return studentDao.findRowsByDepartment();
    }
}
