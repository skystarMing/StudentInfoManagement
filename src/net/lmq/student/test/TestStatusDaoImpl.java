package net.lmq.student.test;

/**
 * 时间：2019.6.18
 * 功能：测试状态访问数据接口实现类
 */

import net.lmq.student.bean.Status;
import net.lmq.student.dao.impl.StatusDaoImpl;
import net.lmq.student.dao.StatusDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestStatusDaoImpl {
    @Before
    public void beforeTest(){
        System.out.println("开始");
    }

    @Test
    public void testfindById(){
        StatusDao dao=new StatusDaoImpl();
        Status status=dao.findById(1);
        System.out.println(status);
    }
    @Test
    public void testupdate(){
        StatusDao dao=new StatusDaoImpl();
        Status status=dao.findById(1);
        status.setAuthor("李建");
        dao.update(status);
        status=dao.findById(1);
        System.out.println(status);
    }

    @After
    public void afterTest(){
        System.out.println("结束");
    }
}
