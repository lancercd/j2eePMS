import request from '../utils/request.js';
import Message from '../utils/Message.js';

const loginForm = document.getElementById("login");
const registerForm = document.getElementById("register");

function toggleForm(){
    let container = document.querySelector('.container');
    container.classList.toggle('active')
}

const message = new Message();

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
            window.location.href="/index";
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