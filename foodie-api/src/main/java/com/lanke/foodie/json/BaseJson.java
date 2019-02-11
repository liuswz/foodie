package com.lanke.foodie.json;
/**
 *
 * @author lvgang
 * @name BaseJSON
 * @time 2018年9月19日下午2:57:03
 * @email lvgang1@yonyou.com
 * @descrpition BasJson封装工具类
 */
public class BaseJson {
    protected String message="成功"; //返回信息
    protected int code=0; //默认0为成功
    protected Object result;

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
