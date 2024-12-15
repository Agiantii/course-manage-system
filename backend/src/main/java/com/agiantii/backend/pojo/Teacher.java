package com.agiantii.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_teacher")
public class Teacher {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
}
