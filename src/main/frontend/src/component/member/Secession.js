import React , { useState , useEffect } from 'react'
import axios from 'axios'
export default function Secession( props ) {

    let user = JSON.parse( sessionStorage.getItem("login_token") )
    console.log(user.mno)

    const onDelete =()=>{
        let memail = document.querySelector(".memail").value;
        let mpassword = document.querySelector(".mpassword").value;

         axios.delete('/member/info',{ params : { mno : user.mno , memail : memail , mpassword : mpassword } })
                    .then( r => { console.log(r);
                    if(r.data == true){
                        alert('삭제성공')
                        logOut();
                    }
                    else{ alert('삭제 실패')}

                    } )


    };
    const logOut = ()=>{
                   sessionStorage.setItem("login_token" , null );
                   axios.get("/member/logout").then( r=>{ console.log(r); });  // 백엔드의 인증세션 지우기
                   window.location.href="/member/login";

                   }

    return(<>
         <h3> 회원탈퇴 </h3>
               <form>
                   아이디[이메일] : <input type="text" className="memail" /><br/>
                   비밀번호 : <input type="text" className="mpassword" />  <br/>
                   <button onClick={ onDelete } type="button"> 탈퇴 </button>
               </form>

    </>)
}