package com.zyxx.xcx.controller;


import com.google.common.collect.Lists;
import com.zyxx.business.entity.NewsInfo;
import com.zyxx.common.config.Page;
import com.zyxx.common.utils.MsRestResultData;
import com.zyxx.common.utils.ResponseResult;
import com.zyxx.common.utils.RestResultData;
import com.zyxx.xcx.controller.request.PageUserParam;
import com.zyxx.xcx.controller.request.RequsetUserParam;
import com.zyxx.xcx.dto.UserDto;
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

@Api(tags = "jj小程序后台")
@Controller
@RequestMapping("/xcx/user/ms/")
public class XcxUserMsController {

    final Logger logger;

    @Autowired
    private XcxUserService xcxService;

    public XcxUserMsController() {
        this.logger = LoggerFactory.getLogger(XcxUserMsController.class);
    }


    @GetMapping("/init")
    public String init(Model model) {
        return "xcx/user/list";
    }

    @GetMapping("add")
    public String add(Model model) {
        return "xcx/user/add";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("data", xcxService.getUser(id));
        return "xcx/user/update";
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("data", xcxService.getUser(id));
        return "xcx/user/detail";
    }

    /**
     *
     */
    @ApiOperation(value = "jj小程序ms后台获取用户列表接口", notes = "jj小程序ms后台获取用户列表接口")
    @PostMapping(value = "/getUserList")
    @ResponseBody
    MsRestResultData getUserInfoList(Integer page, Integer limit, PageUserParam pageUserParam ) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(pageUserParam,userDto);
        Page userDtoPage = xcxService.pageUserList( page, limit,userDto);
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
        return new MsRestResultData(userDtoPage.getItemTotal(),userVoList);

    }

    /**
     *
     */
    @ApiOperation(value = "jj小程序删除用户接口", notes = "jj小程序删除用户接口")
    @GetMapping(value = "/deleteUser/{id}")
    @ResponseBody
    ResponseResult deleteUserInfo(@PathVariable("id") Long userId) {
        boolean b = xcxService.softDeleteUser(userId);
        if (b) {
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }

    }


    @PostMapping("add")
    @ResponseBody
    public ResponseResult add(@RequestBody UserDto userDto) {
        boolean b = xcxService.addUser(userDto);
        if (b) {
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }

    }

    @PostMapping("update")
    @ResponseBody
    public ResponseResult update(@RequestBody UserDto userDto) {
        boolean b = xcxService.updateUser(userDto);
        System.out.println("b="+b);
        if (b) {
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }




}
