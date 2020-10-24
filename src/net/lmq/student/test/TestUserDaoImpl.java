package net.lmq.student.test;

import net.lmq.student.bean.User;
import net.lmq.student.dao.UserDao;
import net.lmq.student.dao.impl.UserDaoImpl;
import org.junit.Test;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class TestUserDaoImpl {
    @Test
    public void testInsert(){
        User user=new User();
        user.setId(10);
        user.setUsername("小王");
        user.setPassword("123456");
        user.setTelephone("12345678910");
        user.setRegisterTime(new Timestamp(new Date().getTime()));

        UserDao dao=new UserDaoImpl();
        int count=dao.insert(user);
        if (count >0){
            System.out.println("恭喜，插入用户数据成功");
        }else{
            System.out.println("插入用户数据失败");

        }
    }

    @Test
    public void testDeleteById(){

        UserDao dao=new UserDaoImpl();
        int id=10;
        int count=dao.deleteById(id);
        if (count >0){
            System.out.println("恭喜，通过ID删除成功");
        }else {
            System.out.println("通过ID删除失败");

        }
    }

    @Test
    public void testUpdate(){
        User user=new User();
        user.setId(8);
        user.setUsername("mm");
        user.setTelephone("123456789910");
        user.setPassword("123456");
        user.setRegisterTime(new Timestamp(new Date().getTime()));

        UserDao dao=new UserDaoImpl();
        int count=dao.update(user);
        if (count >0){
            System.out.println("成功");
        }else {
            System.out.println("失败");

        }
    }

    @Test
    public void testFindById(){
        int id=1;

        UserDao dao=new UserDaoImpl();
        User user=dao.findById(id);
        System.out.println(user);
    }

    @Test
    public void testFindUserAll(){
        UserDao dao=new UserDaoImpl();
        List <User> user=  dao.findAll();
        System.out.println(user );
    }

    @Test
    public void testlogin(){
        UserDao dao=new UserDaoImpl();
        String name="李刚";
        String pass="123";
        User user=dao.login(name,pass);
        System.out.println(user);
    }
}
