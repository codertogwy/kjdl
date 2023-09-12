const validate = (rule, value, callback, reg, message) => {
    if (value) {
        if (!reg.test(value)) {
            callback(new Error(message))
        }
    }
    callback()
}

// 手机号：
export const validateMobilePhone = (rule, value, callback) => {
    const reg = /^1[3456789]\d{9}$/
    validate(rule, value, callback, reg, '请输入有效的手机号码')
}
// 邮箱：
export const validateEmail = (rule, value, callback) => {
    const reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
    validate(rule, value, callback, reg, '请输入有效的邮箱')
}
// 密码：
export const validatePassWord = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('密码格式不正确'))
    } else if (value.length < 6) {
        callback(new Error('密码长度最小6位'))
    } else {
        callback()
    }
}
// 固定电话：
export const validatePhone = (rule, value, callback) => {
    const reg = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/
    validate(rule, value, callback, reg, '请输入正确的座机号格式为：0000-0000000')
}
// 微信号：
export const validateWeiXin = (rule, value, callback) => {
    const reg = /^[a-zA-Z][a-zA-Z0-9_-]{5,19}$/
    validate(rule, value, callback, reg, '请输入正确的微信号码')
}
// IP地址：
export const validateIP = (rule, value, callback) => {
    if (value && "..." === value) {
        callback(new Error('请输入IP地址'))
    }
    if (value && !(/((25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))/).test(value)) {
        callback(new Error('IP地址不规范'))
    } else {
        callback()
    }
}
// 身份证号：
export const validateIDCard = (rule, value, callback) => {
    if (value && (!(/\d{17}[\d|x]|\d{15}/).test(value) || (value.length !== 15 && value.length !== 18))) {
        callback(new Error('身份证号码不规范'))
    } else {
        callback()
    }
}
