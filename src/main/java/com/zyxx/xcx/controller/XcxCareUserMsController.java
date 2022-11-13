package com.zyxx.xcx.controller;


import com.google.common.collect.Lists;
import com.zyxx.business.entity.NewsInfo;
import com.zyxx.common.config.Page;
import com.zyxx.common.utils.LayTableResult;
import com.zyxx.common.utils.MsRestResultData;
import com.zyxx.common.utils.ResponseResult;
import com.zyxx.common.utils.RestResultData;
import com.zyxx.xcx.controller.request.PageUserParam;
import com.zyxx.xcx.dto.UserDto;
import com.zyxx.xcx.entity.CareUser;
import com.zyxx.xcx.service.XcxCareUserService;
import com.zyxx.xcx.service.XcxUserService;
import com.zyxx.xcx.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "jj小程序用户关注后台")
@Controller
@RequestMapping("/xcx/careuser/ms/")
public class XcxCareUserMsController {

    final Logger logger;

    @Autowired
    private XcxCareUserService xcxCareUserService;

    public XcxCareUserMsController() {
        this.logger = LoggerFactory.getLogger(XcxCareUserMsController.class);
    }


    @GetMapping("init")
    public String init(Model model) {
        return "xcx/careuser/list";
    }

    @GetMapping("add")
    public String add(Model model) {
        return "xcx/careuser/add";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("data", xcxCareUserService.getById(id));
        return "xcx/careuser/update";
    }

    @ApiOperation(value = "jj小程序ms后台获取用户关注列表接口", notes = "jj小程序ms后台获取关注用户列表接口")
    @PostMapping("list")
    @ResponseBody
    public MsRestResultData list(Integer page, Integer limit, CareUser careUser) {
        return xcxCareUserService.list(page, limit, careUser);
    }

    @PostMapping("add")
    @ResponseBody
    public ResponseResult add(@RequestBody CareUser careUser) {
        return xcxCareUserService.add(careUser);
    }

    @PostMapping("update")
    @ResponseBody
    public ResponseResult update(@RequestBody CareUser careUser) {
        return xcxCareUserService.update(careUser);
    }

    @GetMapping("del/{id}")
    @ResponseBody
    public ResponseResult del(@PathVariable("id") Long id) {
        return xcxCareUserService.del(id);
    }

}
