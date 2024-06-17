import request from '@/utils/request'

// 查询资讯列表
export function listNews(query) {
  return request({
    url: '/farmlands/news/list',
    method: 'get',
    params: query
  })
}

// 查询资讯详细
export function getNews(id) {
  return request({
    url: '/farmlands/news/' + id,
    method: 'get'
  })
}

// 新增资讯
export function addNews(data) {
  return request({
    url: '/farmlands/news',
    method: 'post',
    data: data
  })
}

// 修改资讯
export function updateNews(data) {
  return request({
    url: '/farmlands/news',
    method: 'put',
    data: data
  })
}

// 删除资讯
export function delNews(id) {
  return request({
    url: '/farmlands/news/' + id,
    method: 'delete'
  })
}
