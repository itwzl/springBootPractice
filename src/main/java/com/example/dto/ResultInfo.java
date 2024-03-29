package com.example.dto;

import java.io.Serializable;

/**
 * Company: www.leadmap.net
 * Description:
 *
 * @author: 49022
 * @Date: 2019/1/23 13:53
 */
public class ResultInfo<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
