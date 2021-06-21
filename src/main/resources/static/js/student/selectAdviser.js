import request from '../utils/request.js';
import Message from '../utils/Message.js';

$('#seach').click(function () {
    const semester = $("#semester").val();
    window.location.href = "/student/selectAdviser?semesterId="+semester;
});

const selectBtns = document.querySelectorAll('.select-adviser-btn');
const message = new Message();
selectBtns.forEach((el, index) => {
    el.addEventListener('click', (e) => {
        const target = e.target;
        const id = target.dataset.id;
        request({
            url: '/student/adviser/add',
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