package com.zyxx.xcx.service;


import com.zyxx.common.config.Page;
import com.zyxx.xcx.dto.UserDto;

import java.util.List;

public interface XcxUserService {

    public boolean addUser(UserDto userDto);

    public boolean deleteUser(Long userId);

    public boolean updateUser(UserDto userDto);

    UserDto getUser(Long userId);

    List<UserDto> getUserList(UserDto userDto);

    Page<UserDto> pageUserList(Integer pageNum, Integer pageSize, UserDto userDto);

}
