import request from '../utils/request.js';
import Message from '../utils/Message.js';
import { isLoginPage } from '../utils/url.js';

const loginForm = document.getElementById("login");
const registerForm = document.getElementById("register");

function toggleForm(){
    let container = document.querySelector('.container');
    container.classList.toggle('active')
}

const message = new Message();

const isLoginUrl = isLoginPage();

function redirect(){
    if(isLoginUrl){
        window.location.href="/index";
    }else{
        window.location.reload();
    }
}


function onLogin(){
    const username = loginForm.username.value;
    const pwd = loginForm.pwd.value;
    if(username.trim() === ''){
        message.show({msg: "请输入用户名", type: 'error', closeable: true});
        return false;
    }

    if(pwd.trim() === ''){
        message.show({msg: "请输入密码", type: 'error', closeable: true});
        return false;
    }


    request({
        url: '/login',
        type: 'post',
        data: {
            username,
            pwd
        }
    }).then(
        msg => {
            console.log(msg);
            redirect()
        },
        err => {
            message.show({msg: err.msg, type: 'error', closeable: true});
            console.log(err);
        }
    )

    return false;
}




loginForm.onsubmit = onLogin;
registerForm.onsubmit = () => {}