package com.zyxx.xcx.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserVo implements Serializable {


    private Long id;

    private String createdAt;

    private Long userNo;

    private  String userName;

    private  String nickName;
    /**
     * 0未知 1男 2女
     */
    private  Integer sex;

    private  String sexDesc;
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

    private  String nativeAddressName;


    private  String workProvinceName;

    private  String workCityName;

    private  String workAreaName;

    private  String workAddressName;

    /**
     * 星座
     */
    private String constellation;

    private  String aboutYou;

    private  String aboutOther;

    private  String mainImage;

    private  String lifeImage;

    private  String fulllifeImage;

    private List<String> lifeImageList;

    private List<String> fullLifeImageList;


}
