package net.lmq.student.service;
/**
 * 时间：2019.6.19
 * 功能：状态服务接口
 */

import net.lmq.student.bean.Status;

public interface StatusService {
    Status findStatusById(int id);
    int updateStstus(Status status);
}
