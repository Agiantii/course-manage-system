<template>
  <div class="course-edit">
    <el-card class="edit-card">
      <template #header>
        <div class="card-header">
          <span>{{ isEdit ? '编辑课程' : '添加课程' }}</span>
        </div>
      </template>
      
      <el-form
        ref="formRef"
        :model="courseForm"
        :rules="rules"
        label-width="100px"
        class="edit-form"
      >
        <el-form-item label="课程名称" prop="name">
          <el-input v-model="courseForm.name" placeholder="请输入课程名称" />
        </el-form-item>
        
        <el-form-item label="学分" prop="credit">
          <el-input-number 
            v-model="courseForm.credit" 
            :min="1" 
            :max="10"
            :precision="1"
            :step="0.5"
          />
        </el-form-item>
        
        <el-form-item label="课程容量" prop="studentLimit">
          <el-input-number 
            v-model="courseForm.studentLimit" 
            :min="1" 
            :max="200"
            :step="5"
          />
        </el-form-item>
        
        <el-form-item label="开课学期" prop="term">
          <el-select v-model="courseForm.term" class="w-full">
            <el-option label="2023-2024学年第一学期" value="2023-1" />
            <el-option label="2023-2024学年第二学期" value="2023-2" />
          </el-select>
        </el-form-item>
        
        <el-form-item label="课程描述" prop="description">
          <el-input
            v-model="courseForm.description"
            type="textarea"
            rows="4"
            placeholder="请输入课程描述"
          />
        </el-form-item>
        
        <el-form-item>
          <el-button type="primary" @click="handleSubmit" :loading="loading">
            {{ isEdit ? '保存修改' : '添加课程' }}
          </el-button>
          <el-button @click="handleCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { teacherApi } from '@/api/teacher'
import './edit.scss'

const route = useRoute()
const router = useRouter()
const formRef = ref(null)
const loading = ref(false)

// 判断是否为编辑模式
const isEdit = computed(() => route.query.courseId)

// 课程表单
const courseForm = reactive({
  id: '',
  name: '',
  credit: 2,
  studentLimit: 50,
  term: '2023-2',
  description: '',
  teacherId: ''
})

// 表单验证规则
const rules = {
  name: [{ required: true, message: '请输入课程名称', trigger: 'blur' }],
  credit: [{ required: true, message: '请选择学分', trigger: 'change' }],
  studentLimit: [{ required: true, message: '请设置课程容量', trigger: 'change' }],
  term: [{ required: true, message: '请选择开课学期', trigger: 'change' }],
  description: [{ required: true, message: '请输入课程描述', trigger: 'blur' }]
}

// 获取课程信息
const fetchCourseInfo = async () => {
  try {
    loading.value = true
    const courseId = route.query.courseId
    const res = await teacherApi.getCourseById(courseId)
    
    if (res && res.data) {
      Object.assign(courseForm, res.data)
    }
  } catch (error) {
    console.error('获取课程信息失败:', error)
    ElMessage.error('获取课程信息失败')
  } finally {
    loading.value = false
  }
}

// 处理表单提交
const handleSubmit = async () => {
  if (!formRef.value) return
  
  try {
    await formRef.value.validate()
    loading.value = true
    
    const teacherId = localStorage.getItem('uid')
    const courseData = {
      ...courseForm,
      teacherId: parseInt(teacherId)
    }
    
    if (isEdit.value) {
      await teacherApi.updateCourse(courseData)
      ElMessage.success('更新成功')
    } else {
      await teacherApi.addCourse(courseData)
      ElMessage.success('添加成功')
    }
    
    router.push('/teacher/courses')
  } catch (error) {
    console.error('保存课程失败:', error)
    ElMessage.error('保存失败')
  } finally {
    loading.value = false
  }
}

// 处理取消
const handleCancel = () => {
  router.push('/teacher/courses')
}

// 初始化
onMounted(() => {
  if (isEdit.value) {
    fetchCourseInfo()
  }
})
</script> 