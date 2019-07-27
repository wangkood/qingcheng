package cn.wangxing.qing.pojo.other;

import java.io.Serializable;

public class SwapData<T> implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    public static final SwapData SUCCESS = new SwapData("0", "SUCCESS");
    public static final SwapData FAIL = new SwapData("400", "FAIL ");
    public static final SwapData SERVICERROR = new SwapData("500", "SERVICE ERROR");

    public static final String SUCCESS_CODE= "0";
    public static final String EXCEPTION_CODE= "500";


    private String errorCode;
    private String message;
    private T   obj;

    private PageInfo<T> pageInfo;

    @Override
    public String toString() {
        return "SwapData{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", obj=" + obj +
                ", pageInfo=" + pageInfo +
                '}';
    }

    // 构造方法
    public SwapData() {}
    public SwapData(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
    public SwapData(String errorCode, String message, T obj) {
        this.errorCode = errorCode;
        this.message = message;
        this.obj = obj;
    }
    public SwapData(String errorCode, String message, T obj,PageInfo<T> pageInfo) {
        this.errorCode = errorCode;
        this.message = message;
        this.obj = obj;
        this.pageInfo = pageInfo;
    }

    // 后期设置值
    public SwapData value(T obj){
        this.obj = obj;
        return this;
    }
    public SwapData value(String errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
        return this;
    }
    public SwapData value(String errorCode, String message, T obj){
        this.errorCode = errorCode;
        this.message = message;
        this.obj = obj;
        return this;
    }

    // get&set
    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public T getObj() { return obj; }
    public void setObj(T obj) { this.obj = obj; }

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
