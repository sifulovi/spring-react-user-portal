import axios from "axios";

const API_SERVER = "http://localhost:8080"

export default class UserActions {

    static registerUser(payload) {
        return axios({
            url: `${API_SERVER}/registration`,
            method: "POST",
            data: payload
        });
    }

    static loginUser(payload) {
        let querystring = require('querystring');
        let newObj = {
            ...payload,
            "grant_type": 'password'
        };
        return axios({
            url: `${API_SERVER}/oauth/token`,
            method: "POST",
            data: querystring.stringify(newObj),
            headers: {
                Authorization: 'Basic dXNlclBvcnRhbDpzZWNyZXQ=',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        });
    }

    static findUserDetails() {
        let token = `Bearer ${sessionStorage.getItem("token")}`
        return axios({
            url: `${API_SERVER}/profile`,
            method: "GET",
            params: {email: sessionStorage.getItem("email")},
            headers: {
                Authorization: token,
                'Content-Type': 'application/json'
            }
        });
    }

    static logout() {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("email");
    }

    static isAuthenticated() {
        let user = sessionStorage.getItem("token");
        return !!user;
    }
}
