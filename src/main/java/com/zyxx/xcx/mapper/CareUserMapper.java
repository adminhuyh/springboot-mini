package com.zyxx.xcx.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyxx.xcx.entity.CareUser;
import com.zyxx.xcx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CareUserMapper extends BaseMapper<CareUser> {


    public List<User> getCareUserPageList(Integer pageNum, Integer pageSize, Long  userNo);

    public Integer countCareUserPageList(Integer pageNum, Integer pageSize,Long userNo);
}
