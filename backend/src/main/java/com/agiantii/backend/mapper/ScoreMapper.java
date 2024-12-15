package com.agiantii.backend.mapper;

import com.agiantii.backend.pojo.Score;
import com.agiantii.backend.pojo.vo.ScoreVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScoreMapper {
    @Insert("insert into t_score (course_id, student_id,teacher_id,score) values (#{courseId},#{studentId},#{teacherId},#{score})")
    void insert(Score score);

    @Delete("delete from t_score where id = #{id}")
    void delete(Score score);
    @Delete("delete from t_score where student_id = #{studentId}")
    void deleteByStudentId(@Param("studentId") Integer studentId);
    @Delete("delete from t_score where teacher_id = #{teacherId}")
    void deleteByTeacherId(@Param("teacherId") Integer teacherId);
    @Delete("delete from t_score where course_id = #{courseId}")
    void deleteByCourseId(@Param("courseId") Integer courseId);
    @Delete("delete from t_score where course_id = #{courseId} and student_id = #{studentId} and teacher_id = #{teacherId}")
    void deleteByCourseIdAndStudentIdAndTeacherId(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId, @Param("teacherId") Integer teacherId);


    @Update("update t_score set score = #{score} where course_id = #{courseId} and student_id = #{studentId}")
    void update(Score score);

    @Select("select score.*,course.name as courseName,student.name as studentName,teacher.name as teacherName from t_score score left join t_course course on score.course_id = course.id left join t_student student on score.student_id = student.id left join t_teacher teacher on score.teacher_id = teacher.id where student.id = #{studentId}")
    List<ScoreVo> selectByStudentId(@Param("studentId") Integer studentId);

    @Select("select score.*,course.name as courseName,student.name as studentName,teacher.name as teacherName from t_score score left join t_course course on score.course_id = course.id left join t_student student on score.student_id = student.id left join t_teacher teacher on score.teacher_id = teacher.id where student.name like concat('%',#{studentName},'%')")
    List<ScoreVo> selectByStudentName(String studentName);
    @Select("select score.*,course.name as courseName,student.name as studentName,teacher.name as teacherName from t_score score left join t_course course on score.course_id = course.id left join t_student student on score.student_id = student.id left join t_teacher teacher on score.teacher_id = teacher.id where teacher.id = #{teacherId}")
    List<ScoreVo> selectByTeacherId(@Param("teacherId") Integer teacherId);

    @Select("select score.*,course.name as courseName,student.name as studentName,teacher.name as teacherName from t_score score left join t_course course on score.course_id = course.id left join t_student student on score.student_id = student.id left join t_teacher teacher on score.teacher_id = teacher.id where teacher.id = #{teacherId} and course.id = #{courseId}")
    List<ScoreVo> selectByTeacherIdAndCourseId(@Param("teacherId")Integer teacherId, @Param("courseId") Integer courseId);
    @Select("select score.*,course.name as courseName,student.name as studentName,teacher.name as teacherName from t_score score left join t_course course on score.course_id = course.id left join t_student student on score.student_id = student.id left join t_teacher teacher on score.teacher_id = teacher.id where course.name like concat('%',#{courseName},'%')")
    List<ScoreVo> selectByCourseName(String courseName);
        // This interface method retrieves a list of ScoreVo objects based on a given course ID.
    // This is a method for selecting score records by course ID
    // It retrieves score, course, student, and teacher names based on the provided course ID
    @Select("SELECT score.*, course.name AS courseName, student.name AS studentName, teacher.name AS teacherName\n" +
            "FROM t_score AS score\n" +
            "LEFT JOIN t_course AS course ON score.course_id = course.id\n" +
            "LEFT JOIN t_student AS student ON score.student_id = student.id\n" +
            "LEFT JOIN t_teacher AS teacher ON score.teacher_id = teacher.id\n" +
            "WHERE course.id = #{courseId}")
    List<ScoreVo> selectByCourseId(@Param("courseId") Integer courseId);


    @Select("select score.*,course.name as courseName,student.name as studentName,teacher.name as teacherName from t_score score left join t_course course on score.course_id = course.id left join t_student student on score.student_id = student.id left join t_teacher teacher on score.teacher_id = teacher.id where course.id = #{courseId} and student.id = #{studentId}")
    ScoreVo selectByCourseIdAndStudentId(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);

    @Select("select score.*,course.name as courseName,student.name as studentName,teacher.name as teacherName from t_score score left join t_course course on score.course_id = course.id left join t_student student on score.student_id = student.id left join t_teacher teacher on score.teacher_id = teacher.id where  student.id = #{studentId} and course.term <= #{term}")
    List<ScoreVo> selectByStudentIdAndTerm(@Param("studentId") Integer studentId,@Param("term") String term);
    @Select("select count(*) from t_score where course_id = #{courseId}  and teacher_id = #{teacherId}")
    Integer countByCourseIdAndTeacherId(@Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId);
    @Select("select score.*,course.name as courseName,student.name as studentName,teacher.name as teacherNamefrom t_score score left join t_course course on score.course_id = course.id left join t_student student on score.student_id = student.id left join t_teacher teacher on score.teacher_id = teacher.id")
    List<ScoreVo> selectAll();
}
