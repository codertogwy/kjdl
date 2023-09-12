const key = {
    token: 'token',
    name: 'name',
}
export const getToken = () => {
    return sessionStorage.getItem(key.token)
}
export const setToken = (token) => {
    sessionStorage.setItem(key.token, token)
}
export const removeToken = () => {
    sessionStorage.removeItem(key.token)
}

export const getName = () => {
    return sessionStorage.getItem(key.name)
}
export const setName = (name) => {
    sessionStorage.setItem(key.name, name)
}
export const removeName = () => {
    sessionStorage.removeItem(key.name)
}