import axios from 'axios';
import qs from 'qs';

let base = 'api';
let auth = 'auth'

export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data); };

export const GetFoodList = params => { return axios.get(`${base}/food?name=${params}`); };

export const SuggestSearch = params => { return axios.get(`${base}/food/suggest_search?name=${params}`)}


export const GetIndex = params => {
  return axios({
    method:'get',
    url:`${base}/index`,
  })
}

export const GetUser = () => {
  return axios({
    method: 'get',
    url:`${auth}/userAuth`
  })
}

export const GetFavoriteList = params => {
  return axios({
    method: 'get',
    url:`${auth}/favoriteList/${params}`
  })
}

export const AddFavorite = (uid, fid) => {
  let postData = qs.stringify({
    uid: uid,
    fid: fid,
  });
  return axios({
    method: 'post',
    url: `${auth}/addFavorite`,
    data: postData
  })
}

export const Logout = () => {
  return axios({
    method: 'get',
    url:`${auth}/logout`
  })
}

export const GoLogin = params => {
  params.rememberMe = true? 'on': false
  let postData = qs.stringify({
    username: params.username,
    password: params.password,
    'remember-me': params.rememberMe
  });
  return axios({
    method:'post',
    url:`${auth}/login`,
    data: postData
  })
}

//export const getUserListPage = params => { return axios.get("http://localhost:8080/student", { params: params }); };

export const getUserListPage = params => { return axios.get(`${base}/student/?start=` + params)};

export const updateStudent = params => {
  return axios({
    method: 'put',
    url: `${base}/student`,
    data: params
})}

//export const removeUser = params => { return axios.get(`${base}/user/remove`, { params: params }); };

export const removeUser = params => { return axios.delete(`${base}/student/` + params)};

export const batchRemoveUser = params => { return axios.get(`${base}/user/batchremove`, { params: params }); };

export const editUser = params => { return axios.get(`${base}/user/edit`, { params: params }); };

//export const addUser = params => { return axios.post(`${base}/student`, { params: params }); };

export const addUser = params => {return axios({
    method: 'post',
    url: `${base}/student`,
    data: params
})}

export const getStudentClass = params => {return axios.get(`${base}/studentclass/${params}`)}
