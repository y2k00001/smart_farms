import request from '@/sheep/request';

export default {
  // 农场详情
  detail: (id, params = {}) =>
    request({
      url: '/h5/service/' + id,
      method: 'GET',
      custom: {
        showLoading: false,
        showError: false,
      },
    }),

    // 农场服务列表
  farmlandService: (id, params = {}) =>
    request({
        url: '/h5/service/farmlandService/' + id,
        method: 'GET',
        custom: {
            showLoading: false,
            showError: false,
        },
    }),
};
