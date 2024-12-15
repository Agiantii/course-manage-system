<template>
  <div class="error-page">
    <div class="error-content">
      <div class="error-code">403</div>
      <div class="error-title">无权访问</div>
      <div class="error-desc">抱歉，您没有权限访问此页面</div>
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