package com.zyxx.xcx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyxx.business.entity.NewsInfo;
import com.zyxx.common.shiro.SingletonLoginUtils;
import com.zyxx.common.utils.LayTableResult;
import com.zyxx.common.utils.MsRestResultData;
import com.zyxx.common.utils.ResponseResult;
import com.zyxx.sys.entity.SysDictDetail;
import com.zyxx.sys.mapper.SysDictDetailMapper;
import com.zyxx.xcx.entity.CareUser;
import com.zyxx.xcx.mapper.CareUserMapper;
import com.zyxx.xcx.service.XcxCareUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class XcxCareUserServiceImpl extends ServiceImpl<CareUserMapper, CareUser> implements XcxCareUserService {
    @Autowired
    private CareUserMapper careUserMapper;


    @Override
    public MsRestResultData list(Integer page, Integer limit, CareUser careUser) {
        LambdaQueryWrapper<CareUser> queryWrapper = new LambdaQueryWrapper<>();
        if (null != careUser.getUserNo()) {
            queryWrapper.like(CareUser::getUserNo, careUser.getUserNo());
        }
        if (null != careUser.getCareUserNo()) {
            queryWrapper.like(CareUser::getCareUserNo, careUser.getCareUserNo());
        }
        queryWrapper.orderByDesc(CareUser::getCreatedAt);
        return new MsRestResultData<>(this.page(new Page<>(page, limit), queryWrapper));
    }

    @Override
    public ResponseResult add(CareUser careUser) {
        if (null == careUser) {
            return ResponseResult.error("数据错误");
        }
        if (null == careUser.getCareUserNo()) {
            return ResponseResult.error("请输入被关注者用户编号");
        }
        if (null == careUser.getUserNo()) {
            return ResponseResult.error("请输入关注者用户编号");
        }
        this.save(careUser);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult update(CareUser careUser) {
        if (null == careUser || null == careUser.getId() || 0 == careUser.getId()) {
            return ResponseResult.error("数据错误");
        }
        if (null == careUser.getCareUserNo()) {
            return ResponseResult.error("请输入被关注者用户编号");
        }
        this.updateById(careUser);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult del(Long id) {
        if (null == id || 0 == id) {
            return ResponseResult.error("数据错误");
        }
        this.removeById(id);
        return ResponseResult.success();
    }


}
