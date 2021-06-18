
//提示信息
export default class Message{
    constructor() {
        this.name = 'Message';
        this.init();
    }

    init(){
        this.createMsgContainer();
    }

    createMsgContainer(){
        let msgContainer = document.getElementById('message-container');
        if(msgContainer){
            this.msgContainer = msgContainer;
        }else{
            const oDiv = document.createElement('div');
            oDiv.setAttribute('id', 'message-container');
            document.body.appendChild(oDiv);
            this.msgContainer = oDiv;
        }
    }

    show(options) {
        const type={success: 'chenggong',error: 'shibai',info: 'tishi_',warning: 'jinggao'};
        options = Object.assign({
            type: 'info',
            msg: '',
            duration: 2000,
            closeable: false
        }, options);
        options.type = type[options.type] || '';
        const messageEl = document.createElement('div');
        messageEl.style.zIndex = 9999;
        messageEl.className = 'message move-in';
        messageEl.innerHTML = `
            <i class="icon iconfont icon-${options.type}"></i>
            <div class="text">${options.msg}</div>
        `;
        if (options.closeable) {
            let closeEl = document.createElement('i');
            closeEl.className = 'close icon iconfont icon-close1';
            messageEl.appendChild(closeEl);
            closeEl.addEventListener('click', () => {this.close(messageEl)});
        }
        this.msgContainer.appendChild(messageEl);
        if (options.duration > 0){setTimeout(() => {this.close(messageEl)}, options.duration)}
    }

    close(messageEl) {
        messageEl.classList.remove('move-in');
        messageEl.classList.add('move-out');
        messageEl.addEventListener('animationend', () => {
            messageEl.setAttribute('style', 'height: 0; margin: 0');
        });
        messageEl.addEventListener('transitionend', () => {
            messageEl.remove();
        });
    }
}
