import request from '../../utils/request.js';
import Message from '../../utils/Message.js';

const message = new Message();

const editForm = document.getElementById('edit-form');
const route = '/admin/adUser/addTeacher';

function onSubmit() {
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
            window.location.href = '/admin/adUser/teacher';

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
