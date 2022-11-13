package com.zyxx.common.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

@Data
public class RestResultData<T> implements Serializable {
    private static final long serialVersionUID = 7315484448521316815L;

    /**
     * 业务状态 正常
     */
    public static final int STATUS_NORMAL = 1;
    /**
     * 业务状态 异常
     */
    public static final int STATUS_ERROR = 0;

    /**
     * 业务状态 默认正常
     */
    private int status = STATUS_NORMAL;
    /**
     * 错误代码
     */

    private Object errorCode;
    private String errorMessage = "";

    private T content;

    /**
     * 是否正常
     *
     * @return
     */
    public boolean itSuccess() {
        return status == RestResultData.STATUS_NORMAL;
    }

    public RestResultData(){

    }

    /**
     * 是否异常
     *
     * @return
     */
    public boolean itNotSuccess() {
        return status != RestResultData.STATUS_NORMAL;
    }

    public static <T> RestResultData<T> successed() {
        return new RestResultData<T>();
    }

    public static <T> RestResultData<T> successed(T data) {
        RestResultData<T> tchResultData = new RestResultData<>();
        tchResultData.setContent(data);
        return tchResultData;
    }

    public static <T> RestResultData<T> successed(String mesg, T data) {
        RestResultData<T> tchResultData = new RestResultData<>();
        tchResultData.setErrorMessage(mesg);
        tchResultData.setContent(data);
        return tchResultData;
    }

    public static <T> RestResultData<T> successed(RestResultData<T> tchResultData, String mesg) {
        tchResultData = Optional.ofNullable(tchResultData).orElse(new RestResultData<T>());
        tchResultData.setErrorMessage(mesg);
        tchResultData.setStatus(STATUS_NORMAL);
        return tchResultData;
    }

    public static <T> RestResultData<T> failed(String mesg) {
        RestResultData<T> tchResultData = new RestResultData<>();
        tchResultData.setErrorMessage(mesg);
        tchResultData.setStatus(STATUS_ERROR);
        return tchResultData;
    }

    public static <T> RestResultData<T> failed(String code, String mesg) {
        RestResultData<T> tchResultData = new RestResultData<>();
        tchResultData.setErrorCode(code);
        tchResultData.setErrorMessage(mesg);
        tchResultData.setStatus(STATUS_ERROR);
        return tchResultData;
    }

    public static <T> RestResultData<T> failed(Integer code, String mesg) {
        RestResultData<T> tchResultData = new RestResultData<>();
        tchResultData.setErrorCode(code);
        tchResultData.setErrorMessage(mesg);
        tchResultData.setStatus(STATUS_ERROR);
        return tchResultData;
    }

    public static <T> RestResultData<T> failed(Object code, String mesg, T data) {
        RestResultData<T> tchResultData = new RestResultData<>();
        tchResultData.setErrorCode(code);
        tchResultData.setErrorMessage(mesg);
        tchResultData.setStatus(STATUS_ERROR);
        tchResultData.setContent(data);
        return tchResultData;
    }

    public static <T> RestResultData<T> failed(RestResultData<T> tchResultData, Object code, String mesg) {
        tchResultData = Optional.ofNullable(tchResultData).orElse(new RestResultData<T>());
        tchResultData.setErrorMessage(mesg);
        tchResultData.setStatus(STATUS_ERROR);
        tchResultData.setErrorCode(code);
        return tchResultData;
    }

    public static <T> RestResultData<T> failedUnknow(RestResultData<T> tchResultData, String mesg) {
        tchResultData = Optional.ofNullable(tchResultData).orElse(new RestResultData<T>());
        tchResultData.setErrorMessage(mesg);
        tchResultData.setStatus(STATUS_ERROR);
        return tchResultData;
    }

}
