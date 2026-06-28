package com.wjy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wjy.domain.Factory;
import com.wjy.domain.User;
import com.wjy.dto.UserDTO;
import com.wjy.mapper.UserMapper;
import com.wjy.service.IFactoryService;
import com.wjy.service.IUserService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    //
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IFactoryService factoryService;

    @Override
    public User login(User user) {
        /*

        1.
        2.
        ===
        1.
         */
        //1.
        String userName = user.getUserName();
        String userPasswd = user.getUserPasswd();
        if(StringUtils.isBlank(userName) ){
            //
            //
            throw new RuntimeException("用户名为空！");
        }
        if(StringUtils.isBlank(userPasswd) ){
            //
            //
            throw new RuntimeException(("密码为空！"));
        }
        //2.
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userName);
        wrapper.eq(User::getUserPasswd,userPasswd);
        User dbUser = userMapper.selectOne(wrapper);
        //3.
        if(dbUser == null){
            //
            //
            throw new RuntimeException("用户名或密码不正确！");
        }
        return dbUser;
    }



    @Override
    public void register(UserDTO userDTO) {
        /*
            1.
            2.
            3.
            4.
         */
        String userName = userDTO.getUserName();
        String userPasswd = userDTO.getUserPasswd();
        String factoryName = userDTO.getFactoryName();

        //1
        if(StringUtils.isBlank(userName) ){
            throw new RuntimeException("用户名为空！");
        }
        if(StringUtils.isBlank(userPasswd) ){
            throw new RuntimeException("密码为空！");
        }
        if(StringUtils.isBlank(factoryName) ){
            throw new RuntimeException("工厂名称为空！");
        }
        //2
        Factory factory = factoryService.getOneByName(factoryName);
        if(factory != null) {
            throw new RuntimeException("工厂名已经存在！");
        }
        //3
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userName);
        User dbUser = userMapper.selectOne(wrapper);
        if(dbUser != null){
            throw new RuntimeException("用户名已经存在！");
        }
        //4
        //4.1
        factory = new Factory();
        factory.setCreateTime(LocalDateTime.now());
        factory.setFactoryStatus(0);
        factory.setFactoryName(factoryName);
        Integer factoryId = factoryService.save(factory);
        //4.2
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        //
        user.setFlag(0);
        //
        user.setUserStatus(0);
        //
        user.setFactoryId(factoryId);
        //
        user.setUserName(userName);
        //
        user.setUserPasswd(userPasswd);
        userMapper.insert(user);
    }
}

