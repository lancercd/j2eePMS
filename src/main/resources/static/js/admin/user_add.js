import request from '../utils/request.js';
import Message from '../utils/Message.js';


const message = new Message();

const editForm = document.getElementById('edit-form');
const route = '/admin/user/add';

function onSubmit() {
    const name = editForm.name.value;
    if(name.trim() === ''){
        message.show({
            msg: "用户类型内容不能为空！",
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
            window.location.href = '/admin/adUserType';

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
