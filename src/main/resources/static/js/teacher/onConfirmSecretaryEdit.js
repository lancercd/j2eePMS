import request from '../utils/request.js';
import Message from '../utils/Message.js';


const message = new Message();

const editForm = document.getElementById('edit-form');

const route = '/teacher/guidanceAgree/agree/edit/';

function onSubmit() {
    console.log('submit');
    const reqInfo = editForm.reqInfo.value;
    const id = editForm.id.value;
    console.log(id);
    if(reqInfo.trim() === ''){
        message.show({
            msg: "请填写要求!",
            type: 'error',
            closeable: true
        });
        return false;
    }

    request({
        url: route + id,
        type: 'POST',
        data: {
            id,
            reqInfo
        }
    }).then(
        (data) => {
            console.log(data);
            message.show({
                type: 'success',
                msg: data.msg,
                closeable: true,
            });

            window.location.href = '/teacher/guidanceAgree';

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
