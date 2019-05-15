package com.wx.cp.model;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ResultOut
 * @Description TODO
 * @createdate 2019/4/4 星期四 16:27
 */
public class ResultOut<T> {

    public static final int DEFAULT_OK = 200;
    public static final int DEFAULT_NO = 400;
    public static final String DEFAULT_MSG_OK = "请求成功！";
    public static final String DEFAULT_MSG_NO = "请求失败！";

    private int status;
    private String message;
    private T data;

    private static ResultOut result;

    private ResultOut() {}

    public ResultOut(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public static<T> ResultOut<T> getResult(int status, String message, T data) {
        if(null==result){
            return result=new ResultOut<T>(status,message,data);
        }else{
            result.setStatus(status);
            result.setMessage(message);
            result.setData(data);
        }
        return result;
    }

    /**
     *
     * @param <T>
     * @return
     */
    public static <T> ResultOut<T> isOK(){
        return ResultOut.getResult(ResultOut.DEFAULT_OK,ResultOut.DEFAULT_MSG_OK,null);
    }

    /**
     *
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultOut<T> isOK(String message, T data){
        return ResultOut.getResult(ResultOut.DEFAULT_OK,message,data);
    }

    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultOut<T> isOK(T data){
        return ResultOut.getResult(ResultOut.DEFAULT_OK,ResultOut.DEFAULT_MSG_OK,data);
    }

    /**
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultOut<T> isOK(String message){
        return ResultOut.getResult(ResultOut.DEFAULT_OK,ResultOut.DEFAULT_MSG_OK,null);
    }

    /**
     *
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultOut<T> isNo(String message, T data){
        return ResultOut.getResult(ResultOut.DEFAULT_NO,message,data);
    }

    /**
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultOut<T> isNo(String message){
        return  ResultOut.getResult(ResultOut.DEFAULT_NO,message,null);
    }

    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultOut<T> isNo(T data){
        return ResultOut.getResult(ResultOut.DEFAULT_NO,ResultOut.DEFAULT_MSG_NO,data);
    }

    /**
     *
     * @param <T>
     * @return
     */
    public static <T> ResultOut<T> isNo(){
        return ResultOut.getResult(ResultOut.DEFAULT_NO,ResultOut.DEFAULT_MSG_NO,null);
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
