import request from '../utils/request.js';
import Message from '../utils/Message.js';


const addNewsForm = document.getElementById('newsAddForm');

const message = new Message();

function onAddNewsFormSubmit() {
    const title = addNewsForm.title.value;

    const content = addNewsForm.content.value;

    const isActive = addNewsForm.isActive.value;


    request({
        url: '/news/addOrUpdate',
        type: 'POST',
        data: {
            title,
            content,
            isActive
        }
    }).then(
        (msg) => {

            message.show({
                type: 'success',
                msg: msg.msg,
                closeable: true
            });
            window.location.href="/secretary/teachingSecretary";

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


addNewsForm.onsubmit = onAddNewsFormSubmit;