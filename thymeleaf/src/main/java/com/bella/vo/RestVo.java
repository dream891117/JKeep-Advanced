package com.bella.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Date: 2019/7/3-16:30
 * @author Genie
 * Description:  业务响应实体 , JSON序列化对象 (NULL的情况也显示)
 */
@Data
public class RestVo<T>{

    static final int SUCCESS_CODE = 0;

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回消息
     */
    private String msg = new String();

    /**
     * 返回结果
     */
    @JsonProperty
    private T data;

    public RestVo(){
        this.code = SUCCESS_CODE;
    }

    public static RestVo SUCCESS() {
        return new RestVo();
    }

    public static <T> RestVo<T> SUCCESS(T data) {
        RestVo<T> restVo = new RestVo<>();
        restVo.setCode(SUCCESS_CODE);
        restVo.setData(data);
        return restVo;
    }

    public static RestVo SUCCESS(String message) {
        RestVo restVo = new RestVo<>();
        restVo.setCode(SUCCESS_CODE);
        restVo.setMsg(message);
        return restVo;
    }

    public static <T> RestVo<T> SUCCESS(T data, String message) {
        RestVo<T> restVo = new RestVo<>();
        restVo.setCode(SUCCESS_CODE);
        restVo.setData(data);
        restVo.setMsg(message);
        return restVo;
    }

    public static RestVo FAIL(int code, String message) {
        RestVo restVo = new RestVo<>();
        restVo.setCode(code);
        restVo.setMsg(message);
        return restVo;
    }

}
