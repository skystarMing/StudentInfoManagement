package net.lmq.student.test.TestService;

import net.lmq.student.bean.Student;
import net.lmq.student.dao.StudentDao;
import net.lmq.student.service.StudentService;
import net.lmq.student.service.impl.StudentServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * 功能：测试学生服务接口服务类
 */
public class TestStudentServiceImpl {
    @Test
    public void testFindStudentByName(){
        StudentService service=new StudentServiceImpl();
        String name="李";
        List<Student> students=service.findStudentsByName(name);
        for(Student student:students){
            System.out.println(student);
        }
    }
}
