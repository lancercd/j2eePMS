import request from '../utils/request.js';
import Message from '../utils/Message.js';

$('#seach').click(function () {
    const semester = $("#semester").val();
    window.location.href = "/student/selected?semesterId="+semester;
});

const delRoute = '/student/del/';

const delBtns = document.querySelectorAll('.del-adviser-btn');
const message = new Message();
delBtns.forEach((el, index) => {
    el.addEventListener('click', (e) => {
        const target = e.target;
        const id = target.dataset.id;
        request({
            url: delRoute + id,
            type: 'POST',
            data: {
                adviserId: id
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