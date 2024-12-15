package com.agiantii.backend.controller;

import com.agiantii.backend.common.Constant;
import com.agiantii.backend.common.R;
import com.agiantii.backend.mapper.UserMapper;
import com.agiantii.backend.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
//    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserMapper userMapper;
    @PostMapping("/login")
    public R<String> login(Integer id,String password) {
        log.info("user login id:{},password:{}",id,password);
        User user = userMapper.selectUserByIdAndPassword(id,password);
        if(user != null){
            return R.success(user.getRole(), Constant.LOGIN_SUCCESS).add("uid",user.getId());
        }
        return R.error(Constant.LOGIN_FAIL);
    }
    @GetMapping("deleteById")
    public R<String> deleteById(Integer userId){
        User user = userMapper.selectUserById(userId);
        if(user == null){
            return R.error("用户不存在");
        }
        userMapper.deleteUserNyId(userId);
        return R.success( "删除成功");
    }
    @PostMapping("insert")
    public R<String> insert(User user){
            if(userMapper.selectUserById(user.getId()) != null){
                return R.error("id 已经存在，无法插入");
            }
            userMapper.insertUser(user);
            return R.success("插入成功");
    }
    @PostMapping("update")
    public R<String> update(User user){
        if(userMapper.selectUserById(user.getId()) == null){
            return R.error("id 不存在，无法更新");
        }
        log.info("update user:{}",user);
        try{
            userMapper.updateUser(user);
            return R.success("更新成功");
        }catch (Exception e){
            return R.error("更新失败");
        }
    }
}
