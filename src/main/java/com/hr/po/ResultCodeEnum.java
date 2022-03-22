package com.hr.po;

import lombok.Getter;

/**
 * @author lq
 * @date 2021-06-12 - 16:47
 */
@Getter
public enum ResultCodeEnum {


    INTERNAL_SERVER_ERROR(false,300,"失败"),
    FILE_UPLOAD_ERROR(false,300,"文件上传失败"),
    FILE_IMPORT_SUCCESS(true,200,"文件上传成功"),
    CHECK_CODE_SUCCESS(true,200,"成功"),
    CHECK_CODE_ERROR(false,201,"失败");


    private Boolean success;

    private Integer code;

    private String message;

    private ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}
