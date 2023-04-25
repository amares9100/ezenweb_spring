import React, { useState }  from 'react';
import axios from 'axios'
export default function Header( props ) {
    // let [ login , setLogin] = useState( JSON.parse( localStorage.getItem("login_token") ) );
    let [ login , setLogin] = useState( JSON.parse( sessionStorage.getItem("login_token") ) );
    // 로그아웃
    const logOut = ()=>{
        sessionStorage.setItem("login_token" , null );
        axios.get("/member/logout").then( r=>{ console.log(r); });  // 백엔드의 인증세션 지우기
        window.location.href="/member/login";
    }
    return (
        <div>
            <a href="/">/ Home / </a>
            <a href="/board/List"> Board / </a>
            <a href="/admin/DashBoard"> DashBoard / </a>
            <a href="/board/Write"> Write / </a>
            {
                login == null
                ? ( <> <a href="/member/Login"> 로그인 / </a> <a href="/member/Signup"> 회원가입 / </a> </> )
                : ( <> <button onClick={ logOut }>로그아웃 / </button> </> )
            }
        </div>
    )
}