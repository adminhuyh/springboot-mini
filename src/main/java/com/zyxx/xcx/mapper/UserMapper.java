package com.zyxx.xcx.mapper;


import com.zyxx.xcx.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public Integer addUser(User user);


    public Integer deleteUser(Long userId);


    public Integer updateUser(User user);


    public User getUser(Long userId);


    public List<User> getUserList(User user);

    public List<User> getUserPageList(Integer pageNum, Integer pageSize,User user);

    public Integer countUserPageList(Integer pageNum, Integer pageSize,User user);


}
