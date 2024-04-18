import request from '@/utils/request'

// 查询农场地块列表
export function listPlot(query) {
  return request({
    url: '/farmlands/plot/list',
    method: 'get',
    params: query
  })
}

// 查询农场地块详细
export function getPlot(id) {
  return request({
    url: '/farmlands/plot/' + id,
    method: 'get'
  })
}

// 新增农场地块
export function addPlot(data) {
  return request({
    url: '/farmlands/plot',
    method: 'post',
    data: data
  })
}

// 修改农场地块
export function updatePlot(data) {
  return request({
    url: '/farmlands/plot',
    method: 'put',
    data: data
  })
}

// 删除农场地块
export function delPlot(id) {
  return request({
    url: '/farmlands/plot/' + id,
    method: 'delete'
  })
}
