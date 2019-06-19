package com.cp.app.core.comm.basics;

import com.cp.app.core.model.pojo.ApiResponse;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName BaseService
 * @Description TODO
 * @createdate 2019/4/3 星期三 11:01
 */
public abstract class BasicsService {
    /**
     * 装订成功结果集
     * @param data
     * @return
     */
    public ApiResponse bindSucceedResult(Object data){
        return new ApiResponse(data);
    }

    /**
     * 装订失败结果集
     * @param message
     * @return
     */
    public ApiResponse bindFialResult(String message){
        return new ApiResponse(message);
    }
}
