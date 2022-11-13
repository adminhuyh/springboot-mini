package com.zyxx.xcx.controller;


import com.google.common.collect.Lists;
import com.zyxx.common.config.Page;
import com.zyxx.common.utils.RestResultData;
import com.zyxx.xcx.dto.UserDto;
import com.zyxx.xcx.controller.request.PageUserParam;
import com.zyxx.xcx.controller.request.RequsetUserParam;
import com.zyxx.xcx.service.XcxUserService;
import com.zyxx.xcx.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Api(tags = "jj小程序")
@Controller
@RequestMapping("/xcx/user/api")
public class XcxUserApiController {

    final Logger logger;

    @Autowired
    private XcxUserService xcxService;

    public XcxUserApiController() {
        this.logger = LoggerFactory.getLogger(XcxUserApiController.class);
    }



    /**
     *
     */
    @ApiOperation(value = "jj小程序查询用户详情接口", notes = "jj小程序查询用户详情接口")
    @GetMapping(value = "/getUser")
    @ResponseBody
    RestResultData getUserInfo(@RequestParam Long userId) {
        UserVo userVo = new UserVo();
        UserDto user = xcxService.getUser(userId);
        if (Objects.isNull(user)){
            return RestResultData.failed("用户不存在");
        }
        BeanUtils.copyProperties(user,userVo);
        return RestResultData.successed(userVo);
    }

    /**
     *
     */
    @ApiOperation(value = "jj小程序添加用户接口", notes = "jj小程序添加用户接口")
    @PostMapping(value = "/addUser")
    @ResponseBody
    RestResultData addUserInfo(@RequestBody RequsetUserParam userParam) {
        //return RestResultData.successed(true);
//        System.out.println("userParam="+userParam);
//        return RestResultData.successed(true);
        if(Objects.isNull(userParam.getPhoneNumber()) || "".equals(userParam.getPhoneNumber())){
            return RestResultData.failed("手机号码不能为空");
        }
        splitAddress(userParam);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userParam,userDto);

        boolean b = xcxService.addUser(userDto);
        return RestResultData.successed(b);
    }

    /**
     *
     */
    @ApiOperation(value = "jj小程序更新用户接口", notes = "jj小程序更新用户接口")
    @PostMapping(value = "/updateUser")
    @ResponseBody
    RestResultData updateUserInfo(@RequestBody RequsetUserParam userParam) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userParam,userDto);
        boolean b = xcxService.updateUser(userDto);
        return RestResultData.successed(b);
    }


    /**
     *
     */
    @ApiOperation(value = "jj小程序删除用户接口", notes = "jj小程序删除用户接口")
    @GetMapping(value = "/deleteUser")
    @ResponseBody
    RestResultData deleteUserInfo(@RequestParam Long userId) {
        boolean b = xcxService.deleteUser(userId);
        return RestResultData.successed(b);
    }

    /**
     *
     */
    @ApiOperation(value = "jj小程序获取用户列表接口", notes = "jj小程序获取用户列表接口")
    @PostMapping(value = "/getUserList")
    @ResponseBody
    RestResultData<Page<UserVo>> getUserInfoList(@RequestBody PageUserParam pageUserParam) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(pageUserParam,userDto);
        Page userDtoPage = xcxService.pageUserList(pageUserParam.getPageNum(), pageUserParam.getPageSize(), userDto);
        List<UserDto> list = userDtoPage.getList();
        if(CollectionUtils.isEmpty(list)){
            RestResultData.successed();
        }
        List<UserVo> userVoList = Lists.newArrayList();
        list.stream().forEach(k -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(k,userVo);
            if (!CollectionUtils.isEmpty(userVo.getLifeImageList())) {
                userVo.setMainImage(userVo.getLifeImageList().get(0));
            }
            userVoList.add(userVo);
        });
        userDtoPage.setList(userVoList);
        return RestResultData.successed(userDtoPage);
    }


    /**
     * 根据用户编号获取用户所关注的用户信息
     */
    @ApiOperation(value = "jj小程序获取某个用户关注的用户列表接口", notes = "jj小程序获取用户列表接口")
    @PostMapping(value = "/getCareUserList")
    @ResponseBody
    RestResultData<Page<UserVo>> getCareUserList(@RequestBody PageUserParam pageUserParam) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(pageUserParam,userDto);
        Page userDtoPage = xcxService.pageCareUserList(pageUserParam.getPageNum(), pageUserParam.getPageSize(), userDto.getUserNo());
        List<UserDto> list = userDtoPage.getList();
        if(CollectionUtils.isEmpty(list)){
            RestResultData.successed();
        }
        List<UserVo> userVoList = Lists.newArrayList();
        list.stream().forEach(k -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(k,userVo);
            if (!CollectionUtils.isEmpty(userVo.getLifeImageList())) {
                userVo.setMainImage(userVo.getLifeImageList().get(0));
            }
            userVoList.add(userVo);
        });
        userDtoPage.setList(userVoList);
        return RestResultData.successed(userDtoPage);
    }




    private RequsetUserParam splitAddress(RequsetUserParam userParam){
        List<String> workAddress = userParam.getWorkAddress();
        if (!CollectionUtils.isEmpty(workAddress)) {
             if (workAddress.size()>0) {
                 userParam.setWorkProvinceName(workAddress.get(0));
             }
             if (workAddress.size()>1){
                 userParam.setWorkCityName(workAddress.get(1));
             }
             if (workAddress.size()>2){
                 userParam.setWorkAreaName(workAddress.get(2));
             }
        }
        List<String> nativeAddress = userParam.getNativeAddress();
        if (!CollectionUtils.isEmpty(nativeAddress)) {
            if (nativeAddress.size()>0) {
                userParam.setNativeProvinceName(nativeAddress.get(0));
            }
            if (nativeAddress.size()>1){
                userParam.setNativeCityName(nativeAddress.get(1));
            }
            if (nativeAddress.size()>2){
                userParam.setNativeAreaName(nativeAddress.get(2));
            }
        }
        return userParam;
    }




}
