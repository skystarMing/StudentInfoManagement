package net.lmq.student.test.TestService;

import net.lmq.student.bean.College;
import net.lmq.student.service.CollegeService;
import net.lmq.student.service.impl.CollegeServiceImpl;
import org.junit.Test;

/**
 *功能：测试学校服务接口实现类
 * 时间：2019.6.19
 */
public class TestCollegeServiceImpl {
    @Test
    public void testFindCollegeById(){
        CollegeService service=new CollegeServiceImpl();
        College college=service.findCollegeById(1);
        System.out.println(college);
    }

    @Test
    public void testUpdateCollege(){
        CollegeService service=new CollegeServiceImpl();
        College college=service.findCollegeById(1);
        college.setPresident("小王");
        college.setTelephone("313321313");
        int count=service.updateCollege(college);
        if (count >0){
            System.out.println("恭喜，数据更新成功");
            college=service.findCollegeById(1);
            System.out.println(college);
        }else{
            System.out.println("抱歉，数据更新失败");
        }

    }

}
