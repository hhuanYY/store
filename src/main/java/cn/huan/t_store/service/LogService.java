package cn.huan.t_store.service;

import cn.huan.t_store.entity.Log;

/**
 * @ProjectName: t_store
 * @ClassName: LogService
 * @Author: YYH
 * @Description:
 * @CreateDate: 2021/5/8 12:07
 */
public interface LogService {
    /**
     * 添加日志
     * @param log
     */
    void insertLog(Log log);
}
