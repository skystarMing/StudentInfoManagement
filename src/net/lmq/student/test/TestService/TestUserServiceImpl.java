package net.lmq.student.test.TestService;

import net.lmq.student.bean.User;
import net.lmq.student.service.UserService;
import net.lmq.student.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * 功能：测试用户服务接口实现类
 * 时间：2019.6.19
 */
public class TestUserServiceImpl {
    @Test
    public void testLogin(){
        UserService service=new UserServiceImpl();
        String name="";
        String pass="";
        User user=service.login(name,pass);
        if(user !=null){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");

        }

    }
}
