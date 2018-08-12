import axios from 'axios';

let base = 'http://127.0.0.1:8080';

export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data); };

export const GetFoodList = params => { return axios.get(`${base}/food?name=${params}`); };

export const SuggestSearch = params => { return axios.get(`${base}/food/suggest_search?name=${params}`)}

export const getUser = params => {return axios.get(`${base}/student/${params}`)}

//export const getUserListPage = params => { return axios.get("http://localhost:8080/student", { params: params }); };

export const getUserListPage = params => { return axios.get(`${base}/student/?start=` + params)};

export const updateStudent = params => { return axios({
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
