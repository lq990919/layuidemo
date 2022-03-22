package com.hr.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lq
 * @date 2021-07-05 - 17:31
 */
@Data
public class User {

    private int userId;
    private String userName;
    private String userAddress;
    private LocalDateTime userDate;

}
