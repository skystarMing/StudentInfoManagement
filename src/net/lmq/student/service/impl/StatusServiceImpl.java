package net.lmq.student.service.impl;

import net.lmq.student.bean.Status;
import net.lmq.student.dao.StatusDao;
import net.lmq.student.dao.impl.StatusDaoImpl;
import net.lmq.student.service.StatusService;

public class StatusServiceImpl implements StatusService {
    //声明数据访问对象
    private StatusDao statusDao=new StatusDaoImpl();

    @Override
    public Status findStatusById(int id) {
        return statusDao.findById(id);
    }

    @Override
    public int updateStstus(Status status) {
        return statusDao.update(status);
    }
}
