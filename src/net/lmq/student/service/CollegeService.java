package net.lmq.student.service;
/**
 * 时间：2019.6.19
 * 功能：学校服务接口
 */

import net.lmq.student.bean.College;

public interface CollegeService {
    College findCollegeById(int id);
    int updateCollege(College college);
}
