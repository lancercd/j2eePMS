import request from '../../utils/request.js';
import Message from '../../utils/Message.js';

$('#seach').click(function () {
    const number = $("#number").val();
    window.location.href = "/admin/adUser/teacher?number="+number;
});

$('#add').click(function () {
    window.location.href = "/admin/adUser/addTeacher";
});

const delRoute = '/admin/adUser/del/teacher/';

const selectBtns = document.querySelectorAll('.select-adviser-btn');
const message = new Message();
selectBtns.forEach((el, index) => {
    el.addEventListener('click', (e) => {
        const target = e.target;
        const id = target.dataset.id;
        request({
            url: delRoute + id,
            type: 'POST',
            data: {
                semesterId: id
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