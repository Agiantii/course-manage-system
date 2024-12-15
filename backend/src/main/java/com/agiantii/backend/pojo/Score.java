package com.agiantii.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_score")
public class Score {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "course_id")
    private Integer courseId;
    @TableField(value = "student_id")
    private Integer studentId;
    @TableField(value = "teacher_id")
    private Integer teacherId;
    @TableField(value = "score")
    private Integer score;
}
