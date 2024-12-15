import http from '@/api/index.js'

export const adminApi = {
  // 学生管理
  getAllStudents() {
    return http.get('/api/student/selectAll')
  },

  addStudent(student, password) {
    return http.post('/api/student/register', student, {
      params: { password }
    })
  },

  updateStudent(student) {
    return http.post('/api/student/update', student)
  },

  deleteStudent(studentId) {
    return http.get(`/api/student/deleteByStudentId?studentId=${studentId}`)
  },

  // 教师管理
  getAllTeachers() {
    return http.get('/api/teacher/selectAll')
  },

  addTeacher(teacher, password) {
    return http.post('/api/teacher/register', teacher, {
      params: { password }
    })
  },

  updateTeacher(teacher) {
    return http.post('/api/teacher/update', teacher)
  },

  deleteTeacher(teacherId) {
    return http.get(`/api/teacher/deleteByTeacherId?teacherId=${teacherId}`)
  },

  // 课程管理
  getAllCourses() {
    return http.get('/api/course/selectAll')
  },

  addCourse(course) {
    return http.post('/api/course/insert', course)
  },

  updateCourse(course) {
    return http.post('/api/course/update', course)
  },

  deleteCourse(courseId) {
    return http.get(`/api/course/deleteByCourseId?courseId=${courseId}`)
  },

  // 成绩管理
  getScoresByCourseId(courseId) {
    return http.get(`/api/score/selectByCourseId?courseId=${courseId}`)
  },

  getScoreByStudentIdAndTerm(studentId, term) {
    return http.get(`/api/score/selectByStudentIdAndTerm?studentId=${studentId}&term=${term}`)
  },

  updateScore(score) {
    return http.post('/api/score/update', score)
  }
} 