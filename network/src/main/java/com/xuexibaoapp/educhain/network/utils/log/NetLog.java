package com.xuexibaoapp.educhain.network.utils.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @author zhaowenjie
 * @time 2018/3/17 14:23
 * @desc 网络日志
 **/

public class NetLog {
    
    public static void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
    
    public static void d(Object message) {
        Logger.d(message);
    }
    
    public static void e(String message) {
        Logger.e(message);
    }
    
    public static void i(String message) {
        Logger.i(message);
    }
}
