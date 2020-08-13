package com.fengbin.crowd.util;
/*
@作者 冯彬
@时间 2020-06-06-19:21
*/

public class ResultEntity<T> {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILD = "FAILD";

    private String result;
    private String message;
    private T data;

    /*
    请求成功需要返回数据
     */
    public static <E> ResultEntity<E> successWithData(E data){
        return new ResultEntity<E>(SUCCESS,null,data);
    }

    /*
    请求成功且不需要返回数据
     */
    public static <E> ResultEntity<E> successWithoutData(){
        return new ResultEntity<E>(SUCCESS,null,null);
    }

    /**
     * 请求失败
     * @param message
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> faild(String message){
        return new ResultEntity<E>(FAILD,message,null);
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ResultEntity() {
    }
}
