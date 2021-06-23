import request from '../utils/request.js';
import Message from '../utils/Message.js';


const message = new Message();

const editForm = document.getElementById('edit-form');

const route = '/adviser/confirm/student/edit/';

function onSubmit() {
    console.log('submit');
    const sug = editForm.suggestion.value;
    const teacherId = editForm.teacherId.value;
    const id = editForm.id.value;
    console.log(id);
    if(!req || req == 0){
        message.show({
            msg: "请选择评阅老师!",
            type: 'error',
            closeable: true
        });
    }
    if(sug.trim() === ''){
        message.show({
            msg: "请填写意见!",
            type: 'error',
            closeable: true
        });
    }


    const formData = new FormData(editForm);
    request({
        url: route + id,
        type: 'POST',
        is_form_data: true,
        data: formData
    }).then(
        (data) => {
            console.log(data);
            message.show({
                type: 'success',
                msg: data.data,
                closeable: true,
            });

            window.location.href = '/teacher/selected/student';

    },
        (msg) => {
            message.show({
                type: 'error',
                msg: msg.msg,
                closeable: true,
            });
    });

    return false;

}


editForm.onsubmit = onSubmit;
