package net.lmq.student.test;

/**
 * 功能：测试学生访问数据接口实现类
 * 时间：2019.6.18
 */

import net.lmq.student.dao.StudentDao;
import net.lmq.student.bean.Student;
import net.lmq.student.dao.impl.StudentDaoImpl;
import org.junit.Test;


import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class TestStudentDaoImpl {
   @Test
    public void testInsert(){
       //放入数据
       Student student=new Student();//前面括号里面的
       student.setId("18101001");
       student.setName("赖明庆");
       student.setSex("女");
       student.setAge(20);
       student.setDepartment("信息工程");
       student.setClazz("18大数据");
       student.setTelephone("123456789123");

       //应用
       StudentDao dao=new StudentDaoImpl();
       int count=dao.insert(student);
       if (count >0){
           System.out.println("插入成功");
       }else{
           System.out.println("插入失败");
       }
   }
   @Test
    public void testdeleteById(){
       //放入数据
       String id="18101001";

       //应用
       StudentDao dao=new StudentDaoImpl();
       int count=dao.deleteById(id);
       if (count >0){
           System.out.println("删除成功");
       }else{
           System.out.println("删除失败");
       }
   }

   @Test
    public void testDeleteByClacc(){
       String clazz="11软件2班";

       StudentDao dao=new StudentDaoImpl();
       int count=dao.deleteByClass(clazz);
       if (count >0){
           System.out.println("通过班级 删除成功");
       }else{
           System.out.println("通过班级 删除失败");
       }
   }

   @Test
    public void testDelteByDepartment(){
       String department="建筑系";

       StudentDao dao=new StudentDaoImpl();
       int count=dao.deleteByDepartment(department);
       if (count >0){
           System.out.println("恭喜，通过系部，删除成功");
       }else{
           System.out.println("通过系部，删除失败");
       }
   }
   @Test
    public void testUpdate(){
       Student student=new Student();
       student.setId("16090704");
       student.setAge(19);
       student.setSex("女");

       StudentDao dao=new StudentDaoImpl();
       int count=dao.update(student);
       if (count >0){
           System.out.println("恭喜，更新数据成功");
       }else{
           System.out.println("更新数据失败");

       }
   }

   @Test
    public void testFindById(){
       String idd="15012456";

       StudentDao dao=new StudentDaoImpl();
       Student student=dao.findById(idd);
       System.out.println(student);

   }

   @Test
    public void testFindByName(){
       String idd="李煜";

       StudentDao dao=new StudentDaoImpl();
       Student student= dao.findByName(idd);
       System.out.println(student);
   }

   @Test
    public void testFindByNameList(){
       String name="李";

       StudentDao dao=new StudentDaoImpl();
       List<Student> students=dao.findByNname(name);
       for (Student student:students){
           System.out.println(student);
       }

   }

   @Test
    public void testFindByDepartment(){
       String department="信息工程系";

       StudentDao dao=new StudentDaoImpl();
       List<Student> students=dao.findByDepartment(department);
       System.out.println(students);
   }

   @Test
    public void testFindAll(){
       StudentDao dao=new StudentDaoImpl();
       List<Student> students=dao.findAll();
       System.out.println(students);
   }

   @Test
    public void testFindRowsBySex(){
       StudentDao dao=new StudentDaoImpl();
       Vector rows=dao.findRowsBySex();
       Iterator iterator=rows.iterator();
       while (iterator.hasNext()){
           System.out.println(iterator.next());
       }
   }

   @Test
    public void testFindRowsByClazz(){
       StudentDao dao=new StudentDaoImpl();
       Vector rows=dao.findRowsByClass();
       Iterator iterator=rows.iterator();
       while (iterator.hasNext()){
           System.out.println(iterator.next());
       }
   }

   @Test
    public void testFindRowByDepartment(){
       StudentDao dao=new StudentDaoImpl();
       Vector rows=dao.findRowsByDepartment();
       Iterator iterator=rows.iterator();
       while (iterator.hasNext()){
           System.out.println(iterator.next());
       }
   }
}
