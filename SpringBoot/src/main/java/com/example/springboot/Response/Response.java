package com.example.springboot.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "返回结果")
public class Response {
    @ApiModelProperty("状态码 0:成功")
    private int code;
    @ApiModelProperty("消息")
    private String message;
    @ApiModelProperty("返回数据")
    private Object data;

    public Response() {
    }

    public Response(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Response success() {
        Response restResponse = new Response();
        restResponse.setResultCode(ResultCode.SUCCESS);

        return restResponse;
    }

    public static Response success(Object data) {
        Response restResponse = new Response();
        restResponse.setResultCode(ResultCode.SUCCESS);
        restResponse.setData(data);

        return restResponse;
    }

    public static Response fail() {
        Response restResponse = new Response();
        restResponse.setResultCode(ResultCode.FAIL);

        return restResponse;
    }

    public static Response fail(ResultCode resultCode) {
        Response restResponse = new Response();
        restResponse.setResultCode(resultCode);

        return restResponse;
    }

    public static Response fail(String message) {
        Response restResponse = new Response();
        restResponse.setCode(ResultCode.FAIL.code());
        restResponse.setMessage(message);

        return restResponse;
    }

    public static Response fail(Integer code, String message) {
        Response restResponse = new Response();
        restResponse.setCode(code);
        restResponse.setMessage(message);

        return restResponse;
    }

    public static Response fail(ResultCode resultCode, Object data) {
        Response restResponse = new Response();
        restResponse.setResultCode(resultCode);
        restResponse.setData(data);

        return restResponse;
    }

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}