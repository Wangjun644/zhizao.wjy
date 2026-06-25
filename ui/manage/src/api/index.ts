import {deletes, get, getBaseURL, post} from './request'

const HttpManager = {
    // 获取图片信息
    attachImageUrl: (url) => `${getBaseURL()}/${url}`,
    // =======================> 管理员 API 完成
    // 是否登录成功
    userLogin: ({userName, userPasswd}) => post(`user/login`, {
        userName,
        userPasswd
    }),
    //用户注册
    userRegister: ({userName, userPasswd, userRealName, factoryName}) => post(`user/register`, {
        userName,
        userPasswd,
        userRealName,
        factoryName
    }),
    //
    infoIndex:() =>get('info/index'),

}

export {HttpManager}
