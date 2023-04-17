let mno;


function onSignup(){
    let info = {
        memail : document.querySelector(".memail").value,
        mpassword : document.querySelector(".mpassword").value,
        mname : document.querySelector(".mname").value,
        mphone : document.querySelector(".mphone").value
    }


    $.ajax({
        url : "/member/info",
        method : "post" ,
        data : JSON.stringify(info) ,
        contentType : "application/json",
        success : (r)=>{
            console.log(r);
            if( r == true  ){ alert('가입이 되셨습니다.')
            location.href="/";}
            else{ alert('이미 가입된 아이디입니다.')}

            }

    });
};



// .ajax 통해서 로그인
function onLogin(){

    let loginForm = document.querySelectorAll(".loginForm")[0];
    let loginFormData = new FormData(loginForm);


    $.ajax({
     url : "/member/login",
            method : "post" ,
            data : loginFormData ,
            contentType : false,
            processData : false,
            success : (r)=>{
                console.log(r);

        }
    })

}


/*
function onLogin(){
    let info = {
        memail : document.querySelector(".memail").value,
        mpassword : document.querySelector(".mpassword").value
    }
    $.ajax({
        url : "/member/login",
        method : "post" ,
        data : JSON.stringify(info) ,
        contentType : "application/json",
        success : (r)=>{
            console.log(r);
            if( r == true ){ alert('로그인이 되셨습니다.'); }

        }
    })
}
*/
getMember();
function getMember(){
    $.ajax({
        url : "/member/info",
        method : "get",
        success : (r)=>{
            mno = r.mno;
            document.querySelector('.infobox').innerHTML = `${ r.mname }님`
            document.querySelector('.infobox').innerHTML += `<button onclick="getLogout()" type="button">로그아웃</button>`
            if(r.mname != undefined){
            document.querySelector('.infobox').innerHTML += ` <a href="/member/update_info">회원정보 수정</a>`
            document.querySelector('.infobox').innerHTML += ` <a href="/member/delete_info">회원정보 수정</a>`
             }
    }
    })
}
function getLogout(){
    $.ajax({
        url : "/member/logout",
        method : "get" ,
        success : (r)=>{
            location.href="/";
        }
    })
}

// 회원 정보 수정
function onUpdate(){

    console.log( mno );

    let info = {
        mno : mno ,
        mname: document.querySelector('.mname').value,
        mphone: document.querySelector('.mphone').value
    }

    $.ajax({
            url: '/member/info',
            method: 'PUT',
            data: JSON.stringify(info),
            contentType: 'application/json',
            success: (r)=>{
                console.log(r);
                if( r === true ){ alert('회원 정보 수정');
                location.href="/";}
            }

    })
}

// 회원 정보 탈퇴
function onDelete(){
    console.log( mno);
    let mpassword = document.querySelector('.mpassword').value;

    $.ajax({
        url: '/member/info',
        method: 'DELETE',
        data: { "mno": mno, "mpassword": mpassword },
        success: (r)=>{
            console.log(r);
            if( r === true ){ alert('회원 정보 탈퇴');
            getLogout();}
        }

    })
}