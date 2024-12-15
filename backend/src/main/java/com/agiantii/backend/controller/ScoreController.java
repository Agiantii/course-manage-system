package com.agiantii.backend.controller;


import com.agiantii.backend.common.R;
import com.agiantii.backend.mapper.CourseMapper;
import com.agiantii.backend.mapper.ScoreMapper;
import com.agiantii.backend.pojo.Score;
import com.agiantii.backend.pojo.vo.ScoreVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/score")
@Slf4j

public class ScoreController {
    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private CourseMapper courseMapper;
    @PostMapping("insert")
    public R<String> insertScore(@RequestBody Score score) {
        log.info("insert course : {}",score);
        try{
            ScoreVo scoreVo = scoreMapper.selectByCourseIdAndStudentId(score.getCourseId(),score.getStudentId());
            if(scoreVo !=null){
                return R.error("该课程已经选修");
            }
            Integer nowCount = scoreMapper.countByCourseIdAndTeacherId(score.getCourseId(),score.getTeacherId());
            log.info("nowCount:{}",nowCount);
            Integer maxCount = courseMapper.countByCourseIdAndTeacherId(score.getCourseId(),score.getTeacherId());
            log.info("nowCount:{},maxCount:{}",nowCount,maxCount);
            if(nowCount >= maxCount){
                return R.error("该老师的课程数已满");
            }
            scoreMapper.insert(score);
            return R.success("选课成功");
        }catch (Exception e){
            log.error("insert course error : {}",e.getMessage());
            return R.error("insert course error");
        }
    }
    @GetMapping("deleteByCourseIdAndStudentIdAndTeacherId")
    public R<String> deleteScore(Integer courseId,Integer studentId,Integer teacherId){
        log.info("delete course courseId:{},studentId:{},teacherId:{}",courseId,studentId,teacherId);
        try{
            scoreMapper.deleteByCourseIdAndStudentIdAndTeacherId(courseId,studentId,teacherId);
            return R.success("delete course success");
        }catch (Exception e){
            log.error("delete course error : {}",e.getMessage());
            return R.error("delete course error");
        }
    }
    @PostMapping("update")
    public R<String> updateScore(@RequestBody Score score) {
        log.info("update course : {}",score);
        try{
            scoreMapper.update(score);
            return R.success("update course success");
        }catch (Exception e){
            log.error("update course error : {}",e.getMessage());
            return R.error("update course error");
        }
    }
    @GetMapping("selectByCourseId")
    public R<List<ScoreVo>> selectScoreByCourseId(@RequestParam("courseId") int courseId) {
        log.info("getScoreByCourseId courseId:{}",courseId);
        try{
            List<ScoreVo> scoreVos = scoreMapper.selectByCourseId(courseId);
            return R.success(scoreVos,"查询 成绩 成功");
        }catch (Exception e){
            log.error("getScoreByCourseId error : {}",e.getMessage());
            return R.error("查询成绩 失败");
        }
    }
    @GetMapping("selectByStudentId")
    public R<List<ScoreVo>> selectScoreByStudentId(@RequestParam("studentId") int studentId) {
        log.info("getScoreByStudentId studentId:{}",studentId);
        try{
            List<ScoreVo> scoreVos = scoreMapper.selectByStudentId(studentId);
            return R.success(scoreVos,"查询 成绩 成功");
        }catch (Exception e) {
            log.error("getScoreByStudentId error : {}", e.getMessage());
            return R.error("查询成绩 失败");
        }
    }
    @GetMapping("selectByTeacherId")
    public R<List<ScoreVo>> selectScoreByTeacherId(@RequestParam("teacherId") int teacherId) {
        log.info("getScoreByTeacherId teacherId:{}",teacherId);
        try{
                 List<ScoreVo> scoreVos = scoreMapper.selectByTeacherId(teacherId);
                 return R.success(scoreVos,"查询 成绩 成功");
        }catch (Exception e) {
            log.error("getScoreByTeacherId error : {}", e.getMessage());
            return R.error("查询成绩 失败");

        }
    }
    @GetMapping("selectByTeacherIdAndCourseId")
    public R<List<ScoreVo>> selectScoreByTeacherIdAndCourseId(@RequestParam("teacherId") int teacherId, @RequestParam("courseId") int courseId) {
        log.info("getScoreByTeacherIdAndCourseId teacherId:{},courseId:{}",teacherId,courseId);
        try{
            List<ScoreVo> scoreVos = scoreMapper.selectByTeacherIdAndCourseId(teacherId,courseId);
            return R.success(scoreVos,"查询 成绩 成功");
            }
        catch (Exception e) {
                    log.error("getScoreByTeacherIdAndCourseId error : {}", e.getMessage());
                    return R.error("查询成绩 失败");
        }
    }
    @GetMapping("selectByStudentIdAndTerm")
    public R<List<ScoreVo>> selectScoreByStudentIdAndTerm(@RequestParam("studentId") int studentId, @RequestParam("term") String term) {
        log.info("getScoreByStudentIdAndTerm term:{} studentId:{}",term,studentId);
        try{
            List<ScoreVo> scoreVos = scoreMapper.selectByStudentIdAndTerm(studentId,term);
            return R.success(scoreVos,"查询 成绩 成功");
        }catch (Exception e) {
            log.error("getScoreByStudentIdAndTerm error : {}", e.getMessage());
            return R.error("查询成绩 失败");
        }

    }
    @GetMapping("selectByCourseName")
    public R<List<ScoreVo>> selectScoreByCourseName(@RequestParam("courseName") String courseName) {
        log.info("getScoreByCourseName courseName:{}",courseName);
        try{
            List<ScoreVo> scoreVos = scoreMapper.selectByCourseName(courseName);
            return R.success(scoreVos,"查询 成绩 成功");
            }
        catch (Exception e) {
            log.error("getScoreByCourseName error : {}", e.getMessage());
            return R.error("查询成绩 失败");
        }
    }

}
