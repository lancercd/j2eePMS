import request from '../utils/request.js';
import Message from '../utils/Message.js';


const message = new Message();

const editForm = document.getElementById('edit-form');
const route = '/admin/user/edit';

function onSubmit() {
    console.log('submit');

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
