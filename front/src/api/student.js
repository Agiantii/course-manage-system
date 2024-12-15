import http from '@/api/index.js'

export const studentApi = {
  // 根据学号查询学生信息
  selectByStudentId(studentId) {
    return http.get(`/api/student/selectByStudentId?studentId=${studentId}`)
  },

  // 根据姓名查询学生
  selectByStudentName(studentName) {
    return http.get(`/api/student/selectByStudentName?studentName=${studentName}`)
  },

  deleteByStudentId(studentId) {
    return http.get(`/api/student/deleteByStudentId?studentId=${studentId}`)
  },

  register(student, password) {
    return http.post('/api/student/register', student, {
      params: { password }
    })
  },

  update(student) {
    return http.post('/api/student/update', student)
  },

  // 修改密码
  updatePassword(id, oldPassword, newPassword) {
    return http.post('/api/user/update', null, {
      params: { 
        id,
        password: newPassword,
        role: 'student'
      }
    })
  },

  // 课程相关
  getAllCourses() {
    return http.get('/api/course/selectAll')
  },

  getCourseById(courseId) {
    return http.get(`/api/course/selectByCourseId?courseId=${courseId}`)
  },

  getCourseByName(courseName) {
    return http.get(`/api/course/selectByCourseName?courseName=${courseName}`)
  },

  getCourseByTeacherId(teacherId) {
    return http.get(`/api/course/selectByTeacherId?teacherId=${teacherId}`)
  },

  getCourseByTeacherName(teacherName) {
    return http.get(`/api/course/selectByTeacherName?teacherName=${teacherName}`)
  },

  // 成绩相关
  getScoreByStudentId(studentId) {
    return http.get(`/api/score/selectByStudentId?studentId=${studentId}`)
  },

  getScoreByStudentIdAndTerm(studentId, term) {
    return http.get(`/api/score/selectByStudentIdAndTerm?studentId=${studentId}&term=${term}`)
  },

  getScoreByCourseId(courseId) {
    return http.get(`/api/score/selectByCourseId?courseId=${courseId}`)
  },

  getScoreByCourseName(courseName) {
    return http.get(`/api/score/selectByCourseName?courseName=${courseName}`)
  },

  // 工具方法
  calculateGPA(scores) {
    if (!scores.length) return 0
    const totalPoints = scores.reduce((sum, score) => {
      return sum + this.getGradePoint(score.score) * score.credit
    }, 0)
    const totalCredits = scores.reduce((sum, score) => sum + score.credit, 0)
    return totalPoints / totalCredits
  },

  getGradePoint(score) {
    if (score >= 90) return 4.0
    if (score >= 85) return 3.7
    if (score >= 82) return 3.3
    if (score >= 78) return 3.0
    if (score >= 75) return 2.7
    if (score >= 72) return 2.3
    if (score >= 68) return 2.0
    if (score >= 64) return 1.5
    if (score >= 60) return 1.0
    return 0
  },

  // 选课（添加成绩记录）
  addScore(courseId, studentId, teacherId) {
    return http.post('/api/score/insert', {
      courseId,
      studentId,
      teacherId,
      score: 0  // 初始成绩设为0
    })
  },

  // 退课（删除成绩记录）
  deleteScore(courseId, studentId, teacherId) {
    return http.get(`/api/score/deleteByCourseIdAndStudentIdAndTeacherId?courseId=${courseId}&studentId=${studentId}&teacherId=${teacherId}`)
  }
} 