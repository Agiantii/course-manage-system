package com.agiantii.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@TableName("t_student")
@Data
public class Student {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
}
