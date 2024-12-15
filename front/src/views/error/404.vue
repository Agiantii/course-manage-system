<template>
  <div class="error-page">
    <div class="error-content">
      <div class="error-code">404</div>
      <div class="error-title">页面不存在</div>
      <div class="error-desc">抱歉，您访问的页面不存在</div>
      <el-button type="primary" @click="handleBack">返回首页</el-button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import './style.scss'

const router = useRouter()

const handleBack = () => {
  const role = localStorage.getItem('role')
  if (role) {
    router.push(`/${role}/dashboard`)
  } else {
    router.push('/login')
  }
}
</script> 