import http from '@/api/index.js'

export const userApi = {
  // 用户登录
  login(id, password) {
    return http.post('/api/user/login', null, {
      params: { id, password }
    })
  },

  // 更新用户信息
  update(id, password, role) {
    return http.post('/api/user/update', null, {
      params: { id, password, role }
    })
  }
} 