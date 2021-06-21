
export function getRelativeUrl(){
    // console.log(document.domain);
    // console.log(window.location.host);
    // console.log(document.location.toString());

    const url = document.location.toString().split("//")[1];

    const relativeUrl = url.substring(url.indexOf('/'));

    console.log(relativeUrl)

    return relativeUrl;
}


export function isLoginPage(){
    const url = document.location.toString();
    const loginReg = /login/;
    return loginReg.test(url);
}
