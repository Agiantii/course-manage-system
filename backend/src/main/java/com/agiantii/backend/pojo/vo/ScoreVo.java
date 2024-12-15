package com.agiantii.backend.pojo.vo;

import com.agiantii.backend.pojo.Score;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ScoreVo extends Score{
    private String teacherName;
    private String studentName;
    private String courseName;
}
