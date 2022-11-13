package com.zyxx.xcx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyxx.common.utils.MsRestResultData;
import com.zyxx.common.utils.ResponseResult;
import com.zyxx.xcx.entity.CareUser;

public interface XcxCareUserService extends IService<CareUser> {
    /**
     * 分页查询
     */
    MsRestResultData list(Integer page, Integer limit, CareUser careUser);

    /**
     * 新增
     */
    ResponseResult add(CareUser careUser);

    /**
     * 修改
     */
    ResponseResult update(CareUser careUser);

    /**
     * 删除
     */
    ResponseResult del(Long id);
}
