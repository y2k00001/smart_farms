import request from '@/utils/request'

// 查询农田租赁信息列表
export function listLessee(query) {
  return request({
    url: '/farmlands/lessee/list',
    method: 'get',
    params: query
  })
}

// 查询农田租赁信息详细
export function getLessee(id) {
  return request({
    url: '/farmlands/lessee/' + id,
    method: 'get'
  })
}

// 新增农田租赁信息
export function addLessee(data) {
  return request({
    url: '/farmlands/lessee/add',
    method: 'post',
    data: data
  })
}

// 修改农田租赁信息
export function updateLessee(data) {
  return request({
    url: '/farmlands/lessee/update',
    method: 'post',
    data: data
  })
}

// 删除农田租赁信息
export function delLessee(id) {
  return request({
    url: '/farmlands/lessee/del/' + id,
    method: 'post'
  })
}
