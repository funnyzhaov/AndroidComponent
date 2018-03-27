package com.xuexibaoapp.educhain.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * @author zhaowenjie
 * @time 2018/3/17 19:05
 * @desc 统一返回格式
 **/

public class BaseResponse<T extends BaseResult> {
    private String msg;
    private boolean success;
    private int status;
    
    @SerializedName("user")    //返回的data数据名可能为user
    private T result;
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public T getResult() {
        return result;
    }
    
    public void setResult(T result) {
        this.result = result;
    }
}
