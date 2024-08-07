import request from '@/utils/request'

// 查询田间管理记录附件列表
export function listFiles(query) {
  return request({
    url: '/farmlands/files/list',
    method: 'get',
    params: query
  })
}

// 查询田间管理记录附件详细
export function getFiles(id) {
  return request({
    url: '/farmlands/files/' + id,
    method: 'get'
  })
}

// 新增田间管理记录附件
export function addFiles(data) {
  return request({
    url: '/farmlands/files',
    method: 'post',
    data: data
  })
}

// 修改田间管理记录附件
export function updateFiles(data) {
  return request({
    url: '/farmlands/files',
    method: 'put',
    data: data
  })
}

// 删除田间管理记录附件
export function delFiles(id) {
  return request({
    url: '/farmlands/files/' + id,
    method: 'delete'
  })
}
