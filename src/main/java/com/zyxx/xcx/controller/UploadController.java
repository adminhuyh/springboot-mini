package com.zyxx.xcx.controller;


import com.zyxx.common.utils.RestResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Api(tags = "jj小程序上传服务接口")
@RestController
@RequestMapping("/xcx/upload")
public class UploadController {


    private static final String imagePath = "static//images/";

    private static final String serverName = "D:/common-project/common-project/freemaker/springboot-mini/src/main/resources/";


    @ApiOperation(value = "jj小程序上传图片接口" ,notes = "jj小程序上传图片接口")
    @PostMapping("/uploadImage")
    public RestResultData uploadImage(@RequestParam("image") MultipartFile image) {
        if (image.getSize() > 1024 * 1024 * 5) {
            return RestResultData.failed("附件大小不能大于5M");
        }
        //下面判断，图片的格式需要是jpg,jpeg,gif,png格式
        String pictureSuffix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".") + 1);
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(pictureSuffix.toUpperCase())) {
            return RestResultData.failed("请选择jpg,jpeg,gif,png格式的图片");
        }
        String fileName = image.getOriginalFilename();
        fileName = ((int) Math.random() * 999) + "_" +fileName;
        String savePath = imagePath+fileName;
        try {
            File f = new File(serverName+savePath);
            image.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            return RestResultData.failed("上传图片失败");
        }
        return RestResultData.successed(savePath);
    }

}
