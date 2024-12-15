<template>
  <div class="dashboard-container">
    <!-- 统计卡片区域 -->
    <el-row :gutter="20" class="dashboard-cards">
      <el-col :span="8">
        <el-card class="data-card">
          <canvas ref="courseCanvas" class="card-canvas"></canvas>
          <div class="card-content">
            <h3>已选课程</h3>
            <div class="number">{{ stats.selectedCourses }}</div>
            <div class="desc">总课程: {{ stats.totalCourses }}</div>
            <el-button type="text" @click="$router.push('/student/my-courses')">
              查看详情
            </el-button>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="data-card">
          <canvas ref="creditCanvas" class="card-canvas"></canvas>
          <div class="card-content">
            <h3>已修学分</h3>
            <div class="number">{{ stats.earnedCredits }}</div>
            <div class="desc">总学分: {{ stats.requiredCredits }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="data-card">
          <canvas ref="gpaCanvas" class="card-canvas"></canvas>
          <div class="card-content">
            <h3>平均绩点</h3>
            <div class="number">{{ stats.gpa.toFixed(2) }}</div>
            <div class="desc">较上学期: {{ stats.gpaChange > 0 ? '+' : '' }}{{ stats.gpaChange.toFixed(2) }}</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 最近课程表 -->
    <el-card class="schedule-card" v-loading="loading">
      <template #header>
        <div class="card-header">
          <span>近期课程</span>
          <el-button text @click="$router.push('/student/my-courses')">
            查看完整课表
          </el-button>
        </div>
      </template>
      <el-table :data="recentCourses" style="width: 100%">
        <el-table-column prop="term" label="学期" width="180" />
        <el-table-column prop="name" label="课程名称" min-width="180" />
        <el-table-column prop="teacherName" label="教师" width="120" />
        <el-table-column prop="credit" label="学分" width="80" align="center" />
        <el-table-column label="状态" width="100" align="center">
          <template #default="{ row }">
            <el-tag :type="row.status === 'active' ? 'success' : 'info'">
              {{ row.status === 'active' ? '进行中' : '未开课' }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, watch } from 'vue'
import { studentApi } from '@/api/student'
import './style.scss'

// 统计数据
const stats = reactive({
  selectedCourses: 0,
  totalCourses: 0,
  earnedCredits: 0,
  requiredCredits: 120,
  gpa: 0,
  gpaChange: 0
})

// 课程列表
const recentCourses = ref([])
const loading = ref(false)

// Canvas 动画
const courseCanvas = ref(null)
const creditCanvas = ref(null)
const gpaCanvas = ref(null)

// 获取统计数据
const fetchStats = async () => {
  try {
    const studentId = localStorage.getItem('uid')
    
    // 获取所有课程
    const allCoursesRes = await studentApi.getAllCourses()
    stats.totalCourses = allCoursesRes.data?.length || 0
    
    // 获取学生成绩
    const res = await studentApi.getScoreByStudentId(studentId)
    
    if (res && res.data) {
      // 获取课程详细信息
      const coursePromises = res.data.map(score => 
        studentApi.getCourseById(score.courseId)
      )
      const courseResults = await Promise.all(coursePromises)
      
      // 计算统计数据
      const courses = courseResults.map((courseRes, index) => ({
        ...courseRes.data,
        score: res.data[index].score
      }))
      
      stats.selectedCourses = courses.length
      
      // 计算已修学分（通过的课程学分之和）
      const passedCourses = courses.filter(course => course.score >= 60)
      stats.earnedCredits = passedCourses.reduce((sum, course) => sum + course.credit, 0)
      
      // 计算GPA
      stats.gpa = studentApi.calculateGPA(courses)
      
      // 计算GPA变化（如果有上学期数据）
      const lastTermGPA = await getLastTermGPA(studentId)
      stats.gpaChange = stats.gpa - lastTermGPA
    }
  } catch (error) {
    console.error('获取统计数据失败:', error)
  }
}

// 获取上学期GPA
const getLastTermGPA = async (studentId) => {
  try {
    const currentTerm = '2023-2' // TODO: 动态获取当前学期
    const lastTerm = '2023-1'    // TODO: 动态计算上学期
    
    const res = await studentApi.getScoreByStudentIdAndTerm(studentId, lastTerm)
    if (res && res.data) {
      // 获取课程详细信息
      const coursePromises = res.data.map(score => 
        studentApi.getCourseById(score.courseId)
      )
      const courseResults = await Promise.all(coursePromises)
      
      const lastTermCourses = courseResults.map((courseRes, index) => ({
        ...courseRes.data,
        score: res.data[index].score
      }))
      
      return studentApi.calculateGPA(lastTermCourses)
    }
    return 0
  } catch (error) {
    console.error('获取上学期GPA失败:', error)
    return 0
  }
}

// 获取近期课程
const fetchRecentCourses = async () => {
  try {
    loading.value = true
    const studentId = localStorage.getItem('uid')
    const res = await studentApi.getScoreByStudentId(studentId)
    
    if (res && res.data) {
      // 获取课程详细信息
      const coursePromises = res.data.map(score => 
        studentApi.getCourseById(score.courseId)
      )
      const courseResults = await Promise.all(coursePromises)
      
      // 合并课程信息
      recentCourses.value = courseResults.map(courseRes => ({
        ...courseRes.data,
        status: 'active' // TODO: 根据实际情况设置状态
      })).slice(0, 5) // 只显示最近5门课程
    }
  } catch (error) {
    console.error('获取课程列表失败:', error)
  } finally {
    loading.value = false
  }
}

// 绘制圆形进度
const drawCircleProgress = (canvas, percentage, color) => {
  const ctx = canvas.getContext('2d')
  const centerX = canvas.width / 2
  const centerY = canvas.height / 2
  const radius = Math.min(centerX, centerY) - 10
  
  // 清空画布
  ctx.clearRect(0, 0, canvas.width, canvas.height)
  
  // 绘制背景圆
  ctx.beginPath()
  ctx.arc(centerX, centerY, radius, 0, Math.PI * 2)
  ctx.strokeStyle = 'rgba(255, 255, 255, 0.1)'
  ctx.lineWidth = 10
  ctx.stroke()
  
  // 绘制进度圆
  ctx.beginPath()
  ctx.arc(centerX, centerY, radius, -Math.PI / 2, (-Math.PI / 2) + (Math.PI * 2 * percentage))
  ctx.strokeStyle = color
  ctx.lineWidth = 10
  ctx.stroke()
}

// 初始化图表
const initCharts = () => {
  // 设置Canvas尺寸
  const canvases = [courseCanvas.value, creditCanvas.value, gpaCanvas.value]
  canvases.forEach(canvas => {
    canvas.width = 120
    canvas.height = 120
  })
  
  // 绘制进度
  drawCircleProgress(courseCanvas.value, stats.selectedCourses / stats.totalCourses, '#67C23A')
  drawCircleProgress(creditCanvas.value, stats.earnedCredits / stats.requiredCredits, '#409EFF')
  drawCircleProgress(gpaCanvas.value, stats.gpa / 4, '#E6A23C')
}

// 监听数据变化更新图表
watch(stats, () => {
  initCharts()
})

// 初始化
onMounted(async () => {
  await fetchStats()
  await fetchRecentCourses()
  initCharts()
})
</script>

<style lang="scss" scoped>
// 删除所有样式代码，移至 style.scss
</style> 