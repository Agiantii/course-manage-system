package com.agiantii.backend.mapper;

import com.agiantii.backend.pojo.Course;
import com.agiantii.backend.pojo.vo.CourseVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface CourseMapper {
    @Insert("insert into t_course(teacher_id,id,name,term,credit,student_limit) values (#{teacherId},#{id},#{name},#{term},#{credit},#{studentLimit})")
    void insert(Course course);

    @Delete("delete from t_course where id = #{id}")
    void deleteByCourseId(Integer id);

    @Delete("delete from t_course where id = #{id} and teacher_id = #{teacherId}")
    void deleteByCourseIdAndTeacherId(@Param("id") Integer id, @Param("teacherId") Integer teacherId);

    @Update("update t_course set name = #{name}, term = #{term}, credit = #{credit},student_limit = #{studentLimit} where id = #{id} and teacher_id = #{teacherId}")
    void update(Course course);

    @Select("select course.*, teacher.name as teacherName from t_course as course left join t_teacher as teacher on course.teacher_id = teacher.id where course.name like concat('%',#{courseName},'%')")
    List<CourseVo> selectByCourseName(String courseName);// with fuzzy search

    @Select("select course.*, teacher.name as teacherName from t_course as course left join t_teacher as teacher on course.teacher_id = teacher.id where teacher.name like concat('%',#{teacherName},'%')")
    List<CourseVo> selectByTeacherName(String teacherName);// with fuzzy search

    @Select("select student_limit from t_course where id = #{courseId} and teacher_id = #{teacherId}")
    Integer countByCourseIdAndTeacherId(@Param("courseId") Integer courseId, @Param("teacherId") Integer teacherId);

    @Select("select course.*, teacher.name as teacherName from t_course as course left join t_teacher as teacher on course.teacher_id = teacher.id where teacher.id = #{teacherId}")
    List<CourseVo> selectByTeacherId(Integer teacherId);// with fuzzy search

    @Select("select course.*, teacher.name as teacherName from t_course as course left join t_teacher as teacher on course.teacher_id = teacher.id where course.id = #{courseId}")
    CourseVo selectByCourseId(Integer courseId);

    @Select("select course.*, teacher.name as teacherName from t_course as course left join t_teacher as teacher on course.teacher_id = teacher.id")
    List<CourseVo> selectAll();
}

