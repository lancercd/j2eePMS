import request from '../utils/request.js';
import Message from '../utils/Message.js';

$('#seach').click(function () {
    const semester = $("#semester").val();
    window.location.href = "/teacher/guidanceAgree?semesterId="+semester;
});

const delRoute = '/adviser/disagreeAdviser';

const delBtns = document.querySelectorAll('.del-adviser-btn');
const message = new Message();
delBtns.forEach((el, index) => {
    el.addEventListener('click', (e) => {
        const target = e.target;
        const id = target.dataset.id;
        console.log(id);
        request({
            url: delRoute,
            type: 'POST',
            data: {
                id: id
            }
        }).then(
            (data) => {
                console.log(data);
                message.show({
                    type: 'success',
                    msg: data.data,
                    closeable: true,
                });
                window.location.reload();

        },
            (msg) => {
                message.show({
                    type: 'info',
                    msg: msg.msg,
                    closeable: true,
                });
        });

    })
})