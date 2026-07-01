import {deletes, get, getBaseURL, post, put} from './request'

const HttpManager = {
    // 获取图片信息
    attachImageUrl: (url) => `${getBaseURL()}/${url}`,
    // =======================> 管理员 API 完成
    // 是否登录成功
    /****************************登录模块*****************************/
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
    /****************************产品模块*****************************/
    //
    getAllProduct: ({productName, productNum}) => post('product/getList',
        {productName, productNum}
    ),

    //
    getProductPage: ({productName, productNum, pageSize, currentPage}) => post('product/getPage',
        {productName, productNum, pageSize, currentPage}
    ),
    //
    deleteProduct: (id) => deletes (`product/${id}`),
    //
    updateProduct: ({id, flag, productNum, productName}) => put (`product/update`, {id, flag, productNum, productName}),
    /******************************设备模块***************************/
    //
    getEquPage: ({equName, pageSize, currentPage}) => post('equ/getPage',
        {equName, pageSize, currentPage}
    ),
}

export {HttpManager}
