import request from '../utils/request.js';
import Message from '../utils/Message.js';

$('#seach').click(function () {
    const document = $("#document").val();
    window.location.href = "/admin/adDocumentType?id="+document;
});

$('#add').click(function () {
    window.location.href = "/admin/doc/add";
});

const delRoute = '/admin/doc/del/';

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