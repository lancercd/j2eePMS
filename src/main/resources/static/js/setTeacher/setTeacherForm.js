import request from '../utils/request.js';
import Message from '../utils/Message.js';


const setAdviserInfo = document.getElementById('setAdviserInfo');

const message = new Message();

function onSetAdviserInfo() {
    const semester = setAdviserInfo.addSemester.value;

    const number = setAdviserInfo.number.value;

    const name = setAdviserInfo.name.value;

    console.log(semester+":"+number+":"+name);

    request({
        url: '/secretary/setAdviser',
        type: 'POST',
        data: {
            semester,
            number,
            name
        }
    }).then(
        (msg) => {
            console.log(semester+":"+number+":"+name)
            message.show({
                type: 'success',
                msg: msg.msg,
                closeable: true
            });
            window.location.href="/secretary/showSemester";

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

setAdviserInfo.onsubmit =  onSetAdviserInfo;