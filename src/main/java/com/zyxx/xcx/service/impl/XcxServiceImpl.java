package com.zyxx.xcx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.zyxx.common.config.Page;
import com.zyxx.common.utils.DateFormatUtil;
import com.zyxx.common.utils.LayTableResult;
import com.zyxx.common.utils.NumberUtil;
import com.zyxx.sys.entity.SysDict;
import com.zyxx.xcx.dto.UserDto;
import com.zyxx.xcx.entity.CareUser;
import com.zyxx.xcx.entity.User;
import com.zyxx.xcx.mapper.CareUserMapper;
import com.zyxx.xcx.mapper.UserMapper;
import com.zyxx.xcx.service.XcxUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class XcxServiceImpl implements XcxUserService {


    final UserMapper userMapper;

    final CareUserMapper careUserMapper;



    public XcxServiceImpl(@Autowired UserMapper userMapper, @Autowired CareUserMapper careUserMapper) {
        this.userMapper = userMapper;
        this.careUserMapper = careUserMapper;
    }


    @Override
    public boolean addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setLifeImage(covertImageListToString(userDto.getLifeImageList()));
        user.setFullLifeImage(covertImageListToString(userDto.getFullLifeImageList()));
        user.setUserNo(NumberUtil.getNumber());
        Integer integer = userMapper.addUser(user);
        if(integer.equals(1)){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        Integer integer = userMapper.deleteUser(id);
        if(integer.equals(1)){
            return true;
        }
        return false;
    }


    @Override
    public boolean softDeleteUser(Long id) {
        Integer integer = userMapper.softDeleteUser(id);
        if(integer.equals(1)){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        if(Objects.isNull(userDto) || Objects.isNull(userDto.getId())){
            return false;
        }
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setLifeImage(covertImageListToString(userDto.getLifeImageList()));
        user.setFullLifeImage(covertImageListToString(userDto.getFullLifeImageList()));
        Integer integer = userMapper.updateUser(user);
        if(integer.equals(1)){
            return true;
        }
        return false;
    }

    @Override
    public UserDto getUser(Long userId) {
        User user = userMapper.getUser(userId);
        if(Objects.isNull(user)){
            return null;
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        userDto.setLifeImageList(covertImageToImagelist(user.getLifeImage()));
        if (!CollectionUtils.isEmpty(userDto.getLifeImageList())) {
            userDto.setLifeImage(userDto.getLifeImageList().get(0));
            userDto.setCreatedAt(DateFormatUtil.parseLocalDateTime(user.getCreatedAt()));
            userDto.setUpdatedAt(DateFormatUtil.parseLocalDateTime(user.getUpdatedAt()));
        }
        userDto.setWorkAddressName(getWorkAddressName(user));
        userDto.setNativeAddressName(getNativeAddressName(user));
        if(!Objects.isNull(user.getSex())){
            if(user.getSex()==1){
                userDto.setSexDesc("男");
            }else if (user.getSex()==2){
                userDto.setSexDesc("女");
            }
        }
        return userDto;
    }

    @Override
    public List<UserDto> getUserList(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        List<User> userList = userMapper.getUserList(user);
        return covertUserList(userList);
    }

    @Override
    public Page<UserDto> pageUserList(Integer pageNum, Integer pageSize, UserDto userDto) {
        pageNum = pageNum == null || pageNum == 0 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize == 0 ? 10 : pageSize;
        Page<UserDto> topicVoPage = new Page<>(pageNum, pageSize);
        if(Objects.isNull(userDto)){
            return topicVoPage;
        }
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        Integer totalPageNum  = userMapper.countUserPageList(topicVoPage.getStartNum(), pageSize, user);
        List<User> userPageList = userMapper.getUserPageList(topicVoPage.getStartNum(), pageSize, user);
        topicVoPage.setItemTotal(totalPageNum);
        topicVoPage.setList(covertUserList(userPageList));
        return topicVoPage;
    }

    @Override
    public Page<UserDto> pageCareUserList(Integer pageNum, Integer pageSize, Long userNo) {
        Page<UserDto> topicVoPage = new Page<>(pageNum, pageSize);
        Integer totalPageNum  = careUserMapper.countCareUserPageList(pageNum, pageSize, userNo);
        List<User> userPageList = careUserMapper.getCareUserPageList(pageNum, pageSize, userNo);
        topicVoPage.setItemTotal(totalPageNum);
        topicVoPage.setList(covertUserList(userPageList));
        return topicVoPage;
    }

    private List<UserDto> covertUserList(List<User> user){
        List<UserDto> userDtoList = Lists.newArrayList();
        user.stream().forEach(k -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(k,userDto);
            userDto.setLifeImageList(covertImageToImagelist(k.getLifeImage()));
            userDto.setCreatedAt(DateFormatUtil.parseLocalDateTime(k.getCreatedAt()));
            userDto.setUpdatedAt(DateFormatUtil.parseLocalDateTime(k.getUpdatedAt()));
            userDtoList.add(userDto);
        });
        return userDtoList;
    }

    private List<String> covertImageToImagelist(String image){
            if (Objects.isNull(image) || image.equals("")) {
                return null;
            }
            String[] split = image.split(",");
            if(split == null || split.length == 0){
                return null;
            }
            return Lists.newArrayList(split);

    }
    public  String covertImageListToString(List<String> imageList){
        if(CollectionUtils.isEmpty(imageList)){
            return "";
        }
        StringBuilder image = new StringBuilder();
        imageList.stream().forEach(k -> image.append(k).append(","));
        return image.substring(0,image.length()-1);
    }
    private String getNativeAddressName(User user){

        StringBuffer sb = new StringBuffer();
        if(StringUtils.isNoneBlank(user.getNativeProvinceName())){
            sb.append(user.getNativeProvinceName()).append(",");
        }
        if (StringUtils.isNoneBlank(user.getNativeCityName())){
            sb.append(user.getNativeCityName()).append(",");
        }
        if (StringUtils.isNoneBlank(user.getNativeAreaName())){
            sb.append(user.getNativeAreaName());
        }
        return sb.toString();
    }
    private String getWorkAddressName(User user){

        StringBuffer sb = new StringBuffer();
        if(StringUtils.isNoneBlank(user.getWorkProvinceName())){
            sb.append(user.getWorkProvinceName()).append(",");
        }
        if (StringUtils.isNoneBlank(user.getWorkCityName())){
            sb.append(user.getWorkCityName()).append(",");
        }
        if (StringUtils.isNoneBlank(user.getWorkAreaName())){
            sb.append(user.getWorkAreaName());
        }
        return sb.toString();
    }
}
