<template>
  <div class="dashboard-container">
    <!-- 统计卡片区域 -->
    <el-row :gutter="20" class="dashboard-cards">
      <el-col :span="6">
        <el-card class="data-card">
          <canvas ref="studentCanvas" class="card-canvas"></canvas>
          <div class="card-content">
            <h3>学生总数</h3>
            <div class="number">{{ stats.totalStudents }}</div>
            <div class="desc">本学期新增: {{ stats.newStudents }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="data-card">
          <canvas ref="teacherCanvas" class="card-canvas"></canvas>
          <div class="card-content">
            <h3>教师总数</h3>
            <div class="number">{{ stats.totalTeachers }}</div>
            <div class="desc">本学期新增: {{ stats.newTeachers }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="data-card">
          <canvas ref="courseCanvas" class="card-canvas"></canvas>
          <div class="card-content">
            <h3>课程总数</h3>
            <div class="number">{{ stats.totalCourses }}</div>
            <div class="desc">本学期开课: {{ stats.activeCourses }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="data-card">
          <canvas ref="scoreCanvas" class="card-canvas"></canvas>
          <div class="card-content">
            <h3>平均成绩</h3>
            <div class="number">{{ stats.averageScore.toFixed(1) }}</div>
            <div class="desc">及格率: {{ stats.passRate.toFixed(1) }}%</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 最近操作记录 -->
    <el-card class="recent-list">
      <template #header>
        <div class="card-header">
          <span>最近操作记录</span>
        </div>
      </template>

      <el-timeline>
        <el-timeline-item
          v-for="(activity, index) in recentActivities"
          :key="index"
          :type="activity.type"
          :timestamp="activity.time"
        >
          {{ activity.content }}
        </el-timeline-item>
      </el-timeline>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { adminApi } from '@/api/admin'
import './style.scss'

// 统计数据
const stats = reactive({
  totalStudents: 0,
  newStudents: 0,
  totalTeachers: 0,
  newTeachers: 0,
  totalCourses: 0,
  activeCourses: 0,
  averageScore: 0,
  passRate: 0
})

// Canvas引用
const studentCanvas = ref(null)
const teacherCanvas = ref(null)
const courseCanvas = ref(null)
const scoreCanvas = ref(null)

// 最近活动
const recentActivities = ref([
  {
    content: '新增学生账号 202401001',
    timestamp: '2024-01-10 10:00:00',
    type: 'primary'
  },
  {
    content: '更新教师信息 张三',
    timestamp: '2024-01-10 09:30:00',
    type: 'success'
  },
  {
    content: '删除课程 高等数学',
    timestamp: '2024-01-10 09:00:00',
    type: 'danger'
  }
])

// 获取统计数据
const fetchStats = async () => {
  try {
    // 获取学生数据
    const studentsRes = await adminApi.getAllStudents()
    if (studentsRes && studentsRes.data) {
      stats.totalStudents = studentsRes.data.length
      // TODO: 计算新增学生数
      stats.newStudents = 10
    }

    // 获取教师数据
    const teachersRes = await adminApi.getAllTeachers()
    if (teachersRes && teachersRes.data) {
      stats.totalTeachers = teachersRes.data.length
      // TODO: 计算新增教师数
      stats.newTeachers = 2
    }

    // 获取课程数据
    const coursesRes = await adminApi.getAllCourses()
    if (coursesRes && coursesRes.data) {
      stats.totalCourses = coursesRes.data.length
      stats.activeCourses = coursesRes.data.filter(
        course => new Date(course.term) > new Date()
      ).length
    }

    // TODO: 获取成绩统计数据
    stats.averageScore = 85.5
    stats.passRate = 95.5

    // 绘制图表
    initCharts()
  } catch (error) {
    console.error('获取统计数据失败:', error)
  }
}

// 绘制圆形进度
const drawCircleProgress = (canvas, percentage, color) => {
  const ctx = canvas.getContext('2d')
  const centerX = canvas.width / 2
  const centerY = canvas.height / 2
  const radius = Math.min(centerX, centerY) - 10
  
  ctx.clearRect(0, 0, canvas.width, canvas.height)
  
  // 背景圆
  ctx.beginPath()
  ctx.arc(centerX, centerY, radius, 0, Math.PI * 2)
  ctx.strokeStyle = 'rgba(255, 255, 255, 0.1)'
  ctx.lineWidth = 10
  ctx.stroke()
  
  // 进度圆
  ctx.beginPath()
  ctx.arc(centerX, centerY, radius, -Math.PI / 2, (-Math.PI / 2) + (Math.PI * 2 * percentage))
  ctx.strokeStyle = color
  ctx.lineWidth = 10
  ctx.stroke()
}

// 初始化图表
const initCharts = () => {
  const canvases = [studentCanvas.value, teacherCanvas.value, courseCanvas.value, scoreCanvas.value]
  canvases.forEach(canvas => {
    canvas.width = 120
    canvas.height = 120
  })
  
  // 绘制进度
  drawCircleProgress(studentCanvas.value, Math.min(stats.totalStudents / 1000, 1), '#409EFF')
  drawCircleProgress(teacherCanvas.value, Math.min(stats.totalTeachers / 100, 1), '#67C23A')
  drawCircleProgress(courseCanvas.value, stats.activeCourses / stats.totalCourses, '#E6A23C')
  drawCircleProgress(scoreCanvas.value, stats.passRate / 100, '#F56C6C')
}

// 初始化
onMounted(() => {
  fetchStats()
})
</script> 