package cn.huan.t_store.mapper;

import cn.huan.t_store.entity.Log;

/**
 * @ProjectName: t_store
 * @ClassName: LogMapper
 * @Author: YYH
 * @Description: xxx
 * @CreateDate: 2021/5/8 11:54
 */
public interface LogMapper {

    /**
     * 添加日志
     * @param log
     * @return
     */
    Integer insertLog(Log log);
}
