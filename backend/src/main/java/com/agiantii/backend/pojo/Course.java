package com.agiantii.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_course")
public class Course {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "teacher_id")
    private int teacherId;
    private String name;
    private int credit;
    private String term;
    @TableField(value = "student_limit")
    private int studentLimit;
}
