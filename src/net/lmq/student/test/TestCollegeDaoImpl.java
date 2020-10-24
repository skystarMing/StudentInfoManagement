package net.lmq.student.test;


import net.lmq.student.bean.College;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.lmq.student.dao.impl.CollegeDaoImpl;
import net.lmq.student.dao.CollegeDao;
/**
 * 功能：测试学校数据访问接口实现类
 */

public class TestCollegeDaoImpl {
    @Before
    public void beforeTest(){

        System.out.println("单元测试开始");
    }


    @Test
    public void testFindById(){
        CollegeDao dao=new CollegeDaoImpl();
        College college=dao.findById(1);
        System.out.println(college);
    }

    @Test
    public void testUpdate(){
        CollegeDao dao=new CollegeDaoImpl();
        College college=dao.findById(1);
        college.setPresident("老师");
        dao.update(college);
        college=dao.findById(1);
        System.out.println(college);

    }

    @After
    public void afterTest(){

        System.out.println("单元测试结束");
    }
}
