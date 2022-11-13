package com.zyxx.sys.controller;


import com.zyxx.common.utils.ResponseResult;
import com.zyxx.sys.entity.SysConfigure;
import com.zyxx.sys.service.SysConfigureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统设置 前端控制器
 * </p>
 *
 * @author lizhou
 * @since 2020-10-20
 */
@Api(tags = "系统设置")
@Controller
@RequestMapping("/sys/sys-configure")
public class SysConfigureController {

    @Autowired
    private SysConfigureService sysConfigureService;

    @ApiOperation(value = "初始化",notes = "初始化")
    @GetMapping("init")
    public String init(Model model) {
        model.addAttribute("data", sysConfigureService.saveSysConfigure());
        return "sys/sysconfigure/info";
    }

    @ApiOperation(value = "更新系统配置",notes = "更新系统配置")
    @PostMapping("update")
    @ResponseBody
    public ResponseResult update(@RequestBody SysConfigure sysConfigure) {
        return sysConfigureService.update(sysConfigure);
    }
}
