package com.hr.po;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lq
 * @date 2021-06-12 - 16:12
 */
@Data
public class R {
    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

//    private Map<String, List<User>> data = new HashMap<String, List<User>>();

//    private Map<String, List<Dict>> data = new HashMap<String, List<Dict>>();

//    private Object dt = new Object();

    public R(){}



//    public static R FileSuccess(Object data){
//        R r = new R();
//        r.setCode(200);
//        r.setDt(data);
//        r.setMessage("文件上传成功！");
//        r.setSuccess(true);
//        return r;
//    }


    public static R fail(){
        R r = new R();
        r.setSuccess(ResultCodeEnum.INTERNAL_SERVER_ERROR.getSuccess());
        r.setCode(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode());
        r.setMessage(ResultCodeEnum.INTERNAL_SERVER_ERROR.getMessage());
        return r;
    }

    public static R failupload(){
        R r = new R();
        r.setSuccess(ResultCodeEnum.FILE_UPLOAD_ERROR.getSuccess());
        r.setCode(ResultCodeEnum.FILE_UPLOAD_ERROR.getCode());
        r.setMessage(ResultCodeEnum.FILE_UPLOAD_ERROR.getMessage());
        return r;
    }

    public static R ok(){
        R r = new R();
        r.setSuccess(ResultCodeEnum.FILE_IMPORT_SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.FILE_IMPORT_SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.FILE_IMPORT_SUCCESS.getMessage());
        return r;
    }

    public static R nice(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }



    public static R error(){
        R r = new R();
        r.setSuccess(ResultCodeEnum.CHECK_CODE_ERROR.getSuccess());
        r.setCode(ResultCodeEnum.CHECK_CODE_ERROR.getCode());
        r.setMessage(ResultCodeEnum.CHECK_CODE_ERROR.getMessage());
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

//    public R data(String key, Object value){
//        this.data.put(key, value);
//        return this;
//    }

//    public R data(Object object){
//        this.setDt(object);
//        return this;
//    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

//    public R data(Map<String, List<Object>> map){
//        this.setData(map);
//        return this;
//    }

//    public R data(String key, List<User> value){
//        this.data.put(key,value);
//        return this;
//    }
//
//    public R data(Map<String, List<User>> map){
//        this.setData(map);
//        return this;
//    }

//    public R data(String key, List<User> value){
//        this.data.put(key,value);
//        return this;
//    }
//
//    public R data(Map<String, List<User>> map){
//        this.setData(map);
//        return this;
//    }
}
