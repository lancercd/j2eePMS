import request from '../utils/request.js';
import Message from '../utils/Message.js';


const message = new Message();

const editForm = document.getElementById('edit-form');

const route = '/student/edit';

function onSubmit() {
    console.log('submit');
    const intro = editForm.intro.value;
    const id = editForm.id.value;
    console.log(id);
    if(intro.trim() === ''){
        message.show({
            msg: "请填写自我介绍",
            type: 'error',
            closeable: true
        });
    }


    const formData = new FormData(editForm);
    request({
        url: route,
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
            window.location.href = '/student/selected';

    },
        (msg) => {
            message.show({
                type: 'info',
                msg: msg.msg,
                closeable: true,
            });
    });

    return false;

}


editForm.onsubmit = onSubmit;
