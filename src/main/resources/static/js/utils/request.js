import {URL} from '../config/config.js';


export default function request(opt){
    let options = opt || {},
        type = (options.type || 'GET').toUpperCase(),
        async = options.async || true,
        data = options.data || null,
        begin = options.begin || function(){},
        finish = options.finish || function(){},
        url = options.url || '',
        is_form_data = options.is_form_data || false;
        if(url === '') throw new Error('url不能为空!');
    begin();
    function formatDatas(obj){
        let str = '';
        for(let key in obj){
            str +=key + '=' + obj[key] + '&';
        }
        return str.replace(/&$/, '');
    }
// console.log('url: ' + url);
    return new Promise((resolve, reject) => {
        const xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
        if(!xhr){
            throw new Error('浏览器不支持ajax    换一下浏览器');
        }

        xhr.open(type, URL+url, async);
        if(type === 'POST' && !is_form_data){
            type === 'POST' && xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;charset=UTF-8');
        }
        xhr.send(type === 'GET' ? null: is_form_data? data:formatDatas(data));
        // xhr.send(data);
        xhr.onload = function(){
            finish();
            if(xhr.status==200){
                const msg = JSON.parse(xhr.responseText);
                (msg.code === 200)? resolve(msg) : reject(msg);
                // console.log(msg);
            }
            else
                reject(JSON.parse(xhr.responseText));
        };
        xhr.onerror = function(){
            reject(this);
        }
    });
};
