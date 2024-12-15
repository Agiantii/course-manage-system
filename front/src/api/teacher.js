import http from '@/api/index.js'

export const teacherApi = {
  // 获取教师信息
  selectByTeacherId(teacherId) {
    return http.get(`/api/teacher/selectByTeacherId?teacherId=${teacherId}`)
  },

  // 根据姓名查询教师
  selectByTeacherName(teacherName) {
    return http.get(`/api/teacher/selectByTeacherName?teacherName=${teacherName}`)
  },

  // 删除教师
  deleteByTeacherId(teacherId) {
    return http.get(`/api/teacher/deleteByTeacherId?teacherId=${teacherId}`)
  },

  // 注册教师
  register(teacher, password) {
    return http.post('/api/teacher/register', teacher, {
      params: { password }
    })
  },

  // 更新教师信息
  update(teacher) {
    return http.post('/api/teacher/update', teacher)
  },

  // 获取教师课程
  getCoursesByTeacherId(teacherId) {
    return http.get(`/api/course/selectByTeacherId?teacherId=${teacherId}`)
  },

  // 添加课程
  addCourse(course) {
    return http.post('/api/course/insert', course)
  },

  // 更新课程
  updateCourse(course) {
    return http.post('/api/course/update', course)
  },

  // 删除课程
  deleteCourse(courseId) {
    return http.get(`/api/course/deleteByCourseId?courseId=${courseId}`)
  },

  // 获取课程学生成绩
  getScoresByCourseId(courseId) {
    return http.get(`/api/score/selectByCourseId?courseId=${courseId}`)
  },

  // 更新学生成绩
  updateScore(score) {
    return http.post('/api/score/update', score)
  }
} 