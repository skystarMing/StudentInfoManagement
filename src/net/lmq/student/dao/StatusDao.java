package net.lmq.student.dao;

/**
 * 时间：2019.6.17
 * 功能：状态数据访问接口
 */

import net.lmq.student.bean.Status;

public interface StatusDao {
    Status findById(int id);
    int update(Status status);

}
