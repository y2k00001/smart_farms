import request from '@/utils/request'

// 查询农作物生长周期信息列表
export function listGrowth(query) {
  return request({
    url: '/farmlands/growth/list',
    method: 'get',
    params: query
  })
}

// 查询农作物生长周期信息详细
export function getGrowth(id) {
  return request({
    url: '/farmlands/growth/' + id,
    method: 'get'
  })
}

// 新增农作物生长周期信息
export function addGrowth(data) {
  return request({
    url: '/farmlands/growth/add',
    method: 'post',
    data: data
  })
}

// 修改农作物生长周期信息
export function updateGrowth(data) {
  return request({
    url: '/farmlands/growth/update',
    method: 'post',
    data: data
  })
}

// 删除农作物生长周期信息
export function delGrowth(id) {
  return request({
    url: '/farmlands/growth/' + id,
    method: 'post'
  })
}
