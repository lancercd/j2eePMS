import request from '../utils/request.js';
import Message from '../utils/Message.js';


const message = new Message();

const editForm = document.getElementById('edit-form');

const route = '/adviser/selected/student/edit/';

function onSubmit() {
    console.log('submit');
    const score = editForm.score.value;
    const sug = editForm.suggestion.value;
    const id = editForm.id.value;
    console.log(id);
    if(!score || score == 0){
        message.show({
            msg: "请输入分数!",
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

            window.location.href = '/adviser/selected/student';

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
