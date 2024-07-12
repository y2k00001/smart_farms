import request from '@/utils/request'

// 查询农田租赁最小面积列表
export function listArea(query) {
  return request({
    url: '/farmlands/area/list',
    method: 'get',
    params: query
  })
}

// 查询农田租赁最小面积详细
export function getArea(id) {
  return request({
    url: '/farmlands/area/' + id,
    method: 'get'
  })
}

// 新增农田租赁最小面积
export function addArea(data) {
  return request({
    url: '/farmlands/area/add',
    method: 'post',
    data: data
  })
}

// 修改农田租赁最小面积
export function updateArea(data) {
  return request({
    url: '/farmlands/area',
    method: 'put',
    data: data
  })
}

// 删除农田租赁最小面积
export function delArea(id) {
  return request({
    url: '/farmlands/area/' + id,
    method: 'delete'
  })
}
