package com.agiantii.backend.controller;

import com.agiantii.backend.common.R;
import com.agiantii.backend.mapper.CourseMapper;
import com.agiantii.backend.pojo.Course;
import com.agiantii.backend.pojo.vo.CourseVo;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseMapper courseMapper;

    @PostMapping("insert")
    public R<String> insertCourse(@RequestBody Course course) {
        log.info("insertCourse {}",course);
        try {
            courseMapper.insert(course);
            return R.success("添加课程成功");
        }
        catch (Exception e) {
            return R.error("该课程已存在");
        }
    }
    @PostMapping("update")
    public R<String> updateCourse(@RequestBody Course course) {
        log.info("updateCourse {}",course);
        try {
            courseMapper.update(course);
            return R.success("更新课程成功");
        }
        catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

//    @GetMapping("deleteByCourseId")
//    public R<String>deleteByCourseId(@RequestParam("courseId") Integer courseId) {
//        log.info("deleteByCourseId: {}", courseId);
//        try{
//            courseMapper.deleteByCourseId(courseId);
//            return R.success("删除成功");
//        }
//        catch (Exception e) {
//            return R.error(e.getMessage());
//        }
//    }
    @GetMapping("deleteByCourseIdAndTeacherId")
    public R<String> deleteByCourseIdAndTeacherId(@RequestParam("courseId") Integer courseId, @Param("teacherId") Integer teacherId){
        log.info("deleteByCourseIdAndTeacherId: teacherId:{}, courseId:{}", teacherId,courseId);
        try{
            courseMapper.deleteByCourseIdAndTeacherId(courseId,teacherId);
            return R.success("删除成功");
        }
        catch (Exception e) {
            return R.error(e.getMessage());
        }
    }
    @GetMapping("selectByCourseName")
    public R<List<CourseVo>> selectCourseByName(String courseName) {
        log.info("selectByCourseName: {}", courseName );
        List<CourseVo> courseVos = courseMapper.selectByCourseName(courseName);
        return R.success(courseVos,"查询成功");
    }

    @GetMapping("selectByTeacherName")
    public R<List<CourseVo>> selectByTeacherName(String teacherName) {
        List<CourseVo> courseVos = courseMapper.selectByTeacherName(teacherName);
        return R.success(courseVos,"查询成功");
    }
    @GetMapping("selectByCourseId")
    public R<CourseVo> selectByCourseId(Integer courseId) {
        CourseVo courseVo = courseMapper.selectByCourseId(courseId);
        return R.success(courseVo,"查询成功");
    }

    @GetMapping("selectByTeacherId")
    public R<List<CourseVo>> selectByTeacherId(Integer teacherId) {
        List<CourseVo> courseVos = courseMapper.selectByTeacherId(teacherId);
        return R.success(courseVos,"查询成功");
    }
    @GetMapping("selectAll")
    public R<List<CourseVo>> selectAll() {
        List<CourseVo> courseVos = courseMapper.selectAll();
        return R.success(courseVos,"查询成功");
    }
}
