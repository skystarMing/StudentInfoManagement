package net.lmq.student.test.TestService;

import net.lmq.student.bean.Status;
import net.lmq.student.service.StatusService;
import net.lmq.student.service.impl.StatusServiceImpl;
import org.junit.Test;

/**
 * 功能：测试状态数据访问接口实现类
 */
public class TestStatusServiceImpl {
    @Test
    public void testFindStatusById(){
        StatusService service=new StatusServiceImpl();
        Status status=service.findStatusById(1);
        System.out.println(status);
    }

    @Test
    public void testUpdateStatus(){
        StatusService service=new StatusServiceImpl();
        Status status=service.findStatusById(1);
        status.setAuthor("小王");
        status.setTelephone("12345678910");
         int count=service.updateStstus(status);
         if (count >0){
             System.out.println("恭喜，数据更新成功");
              status=service.findStatusById(1);
             System.out.println(status);
         }else{
             System.out.println("抱歉，数据更新失败");
         }
    }
}
