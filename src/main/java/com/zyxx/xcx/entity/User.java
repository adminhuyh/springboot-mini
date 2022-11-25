package com.zyxx.xcx.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {

    private  Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long userNo;


    private  String userName;

    private  String nickName;
    /**
     * 0未知 1男 2女
     */
    private  Integer sex;
    /**
     * 微信号
     */
    private  String wxNumber;

    /**
     * 手机号
     */
    private  Long phoneNumber;

    private LocalDate birthDay;

    private  String weight;

    private  String height;

    private  String jobName;

    private  String income;

    private  String nativeProvinceName;

    private  String nativeCityName;

    private  String nativeAreaName;

    private  String workProvinceName;

    private  String workCityName;

    private  String workAreaName;

    /**
     * 星座
     */
    private String constellation;

    private  String aboutYou;

    private  String aboutOther;

    private  String lifeImage;

    private  String fullLifeImage;

    private  String education;

}