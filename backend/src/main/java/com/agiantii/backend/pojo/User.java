package com.agiantii.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_user")
@Data
public class User {
    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;
    private String password;
    private String role;
}
