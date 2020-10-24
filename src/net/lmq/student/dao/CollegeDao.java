package net.lmq.student.dao;

/**
 * 时间：2019.6.17
 * 功能：学校数据访问接口
 */

import net.lmq.student.bean.College;

public interface CollegeDao {
    College findById(int id);
    int update(College college);
}
