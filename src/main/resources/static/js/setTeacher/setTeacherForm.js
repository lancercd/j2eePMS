import request from '../utils/request.js';
import Message from '../utils/Message.js';


const setTeacherForm = document.getElementById('setTeacherForm');

const message = new Message();

function onSetTeacherForm() {
    const number = setTeacherForm.number.value;

    const name = setTeacherForm.name.value;

    const pwd = setTeacherForm.pwd.value;


    request({
        url: '/secretary/setTeacherForm',
        type: 'POST',
        data: {
            number,
            name,
            pwd
        }
    }).then(
        (msg) => {

            message.show({
                type: 'success',
                msg: msg.msg,
                closeable: true
            });
            window.location.href="/secretary/setTeacher";

        },
        (msg) => {
            message.show({
                type: 'error',
                msg: msg.msg,
                closeable: true
            })
        }
    )

    return false;
}

setTeacherForm.onsubmit = onSetTeacherForm;