package com.lanke.foodie.json;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @author lvgang
 * @name BaseJSON
 * @time 2018年9月19日下午2:57:03
 * @email lvgang1@yonyou.com
 * @descrpition BasJson封装工具类
 */

public class BaseJson implements Serializable {
    private String message="成功"; //返回信息
    private int code=0; //默认0为成功
    private Object result;

    public BaseJson(){
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


}
