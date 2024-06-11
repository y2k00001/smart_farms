import request from '@/utils/request'

// 查询田间管理记录列表
export function listRecord(query) {
  return request({
    url: '/farmlands/record/list',
    method: 'get',
    params: query
  })
}

// 查询田间管理记录详细
export function getRecord(id) {
  return request({
    url: '/farmlands/record/' + id,
    method: 'get'
  })
}

// 新增田间管理记录
export function addRecord(data) {
  return request({
    url: '/farmlands/record',
    method: 'post',
    data: data
  })
}

// 修改田间管理记录
export function updateRecord(data) {
  return request({
    url: '/farmlands/record',
    method: 'put',
    data: data
  })
}

// 删除田间管理记录
export function delRecord(id) {
  return request({
    url: '/farmlands/record/' + id,
    method: 'delete'
  })
}
