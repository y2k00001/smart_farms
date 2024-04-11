import request from '@/utils/request'

// 查询农田信息列表
export function listFarmland(query) {
  return request({
    url: '/farmlands/farmland/list',
    method: 'get',
    params: query
  })
}

// 查询农田信息详细
export function getFarmland(id) {
  return request({
    url: '/farmlands/farmland/' + id,
    method: 'get'
  })
}

// 新增农田信息
export function addFarmland(data) {
  return request({
    url: '/farmlands/farmland/add',
    method: 'post',
    data: data
  })
}

// 修改农田信息
export function updateFarmland(data) {
  return request({
    url: '/farmlands/farmland/update',
    method: 'post',
    data: data
  })
}

// 删除农田信息
export function delFarmland(id) {
  return request({
    url: '/farmlands/farmland/del/' + id,
    method: 'post'
  })
}
