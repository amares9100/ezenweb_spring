import React, { useState , useEffect }  from 'react';
import axios from 'axios'
export default function Header( props ) {
    // let [ login , setLogin] = useState( JSON.parse( localStorage.getItem("login_token") ) );
    let [ login , setLogin] = useState( null  ); // 로그인상태
    // 로그아웃
    const logOut = ()=>{
        sessionStorage.setItem("login_token" , null );
        axios.get("/member/logout").then( r=>{ console.log(r); });  // 백엔드의 인증세션 지우기
        window.location.href="/member/login";
        setLogin(null);
    }

    // 로그인 상태 호출
    useEffect(()=>{
        axios.get("/member/info").then( r=>{ console.log(r.data);
            if(r.data != ''){ // 로그인 되어있으면
           // js 로컬스토리지에 저장
            sessionStorage.setItem("login_token" , JSON.stringify( r.data ) );

            // 상태변수 저장
            setLogin(JSON.parse( sessionStorage.getItem("login_token") ) );
        }
        })


    } , [])


    return (
        <div>
            <a href="/">/ Home / </a>
            <a href="/board/List"> Board / </a>
            <a href="/admin/DashBoard"> DashBoard / </a>
            <a href="/board/Write"> Write / </a>
            <a href="/exbook/AppTodo"> AppTodo / </a>
            {
                login == null
                ? ( <> <a href="/member/Login"> 로그인 / </a> <a href="/member/Signup"> 회원가입 / </a> </> )
                : ( <> <a href="/member/Update"> 회원정보수정 / </a> <a href="/member/Secession"> 회원탈퇴 / </a>
                    <button onClick={ logOut }>로그아웃 / </button> </> )
            }
        </div>
    )
}