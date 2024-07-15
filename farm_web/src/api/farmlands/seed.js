import request from '@/utils/request'

// 查询种子信息列表
export function listSeed(query) {
  return request({
    url: '/farmlands/seed/list',
    method: 'get',
    params: query
  })
}

// 查询种子信息详细
export function getSeed(id) {
  return request({
    url: '/farmlands/seed/' + id,
    method: 'get'
  })
}

// 新增种子信息
export function addSeed(data) {
  return request({
    url: '/farmlands/seed/add',
    method: 'post',
    data: data
  })
}

// 修改种子信息
export function updateSeed(data) {
  return request({
    url: '/farmlands/seed',
    method: 'put',
    data: data
  })
}

// 删除种子信息
export function delSeed(id) {
  return request({
    url: '/farmlands/seed/' + id,
    method: 'delete'
  })
}
