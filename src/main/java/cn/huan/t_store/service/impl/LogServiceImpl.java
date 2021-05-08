package cn.huan.t_store.service.impl;

import cn.huan.t_store.entity.Log;
import cn.huan.t_store.mapper.LogMapper;
import cn.huan.t_store.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: t_store 
 * @ClassName: LogServiceImpl
 * @Author: YYH
 * @Description:   
 * @CreateDate: 2021/5/8 12:08
 */
@Service("logServiceImpl")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void insertLog(Log log) {
        Integer integer = logMapper.insertLog(log);
        if (integer != 1) {
            System.err.println("日志添加失败...");
        }
    }
}
