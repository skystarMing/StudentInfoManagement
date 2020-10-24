package net.lmq.student.service.impl;

/**
 * 功能：学校服务接口实现类
 * 时间：2019.6.19
 */

import net.lmq.student.bean.College;
import net.lmq.student.dao.impl.CollegeDaoImpl;
import net.lmq.student.service.CollegeService;
import net.lmq.student.dao.CollegeDao;

public class CollegeServiceImpl implements CollegeService {
    private CollegeDao collegeDao=new CollegeDaoImpl();

    @Override
    public College findCollegeById(int id) {

        return collegeDao.findById(id);
    }

    @Override
    public int updateCollege(College college) {
        return collegeDao.update(college);
    }
}
