import {deletes, get, getBaseURL, post} from './request'

const HttpManager = {
    // 获取图片信息
    attachImageUrl: (url) => `${getBaseURL()}/${url}`,
    // =======================> 管理员 API 完成
    // 是否登录成功
    adminLogin: ({name,password}) => post(`admin/login`, {
        name,
        password
    }),
    //添加产品



}

export {HttpManager}
