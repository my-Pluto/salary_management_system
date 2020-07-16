package com.himoyi.salary_management_system.common;
/**
 * 结果集封装
 * 用于返回所有结果
 */

import lombok.Data;

import java.io.Serializable;

// 让result可以序列化

@Data
public class Result implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public static Result success(String code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.setMsg(msg);
        result.setData(data);
        result.setCode("200");
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setCode("200");
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static Result fail(String code, String msg, Object data) {
        Result result = new Result();
        result.setData(data);
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }

    public static Result fail(String msg, Object data) {
        Result result = new Result();
        result.setCode("404");
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(Object data) {
        Result result = new Result();
        result.setCode("404");
        result.setMsg("fail");
        result.setData(data);
        return result;
    }
}
