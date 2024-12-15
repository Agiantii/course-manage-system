package com.agiantii.backend.pojo.vo;


import com.agiantii.backend.pojo.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CourseVo extends Course {
    private String teacherName;
}
