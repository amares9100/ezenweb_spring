import React , { useState , useEffect } from 'react'
import axios from 'axios'
export default function Update( props ) {

    let user = JSON.parse( sessionStorage.getItem("login_token") )
    console.log(user.mno)


    const memberUpdate =() =>{
        let info = {
                    mno : user.mno,
                    mname : document.querySelector(".mname").value,
                    mphone : document.querySelector(".mphone").value
                }

        axios
                    .put("/member/info" , info )
                    .then( r => {  console.log( r );
                        if( r.data == true  ){
                            alert('수정 성공');window.location.href="/"; // window.location.href="이동할 경로";
                        }else{
                            alert('수정 실패 [ 관리자에게 문의 ]');
                        }
                    })
                    .catch( err => { console.log( err ) });
                }

         return (<>

                <div> 회원정보수정 </div>
                <div> 수정할 이름과 전화번호 입력 </div>
                <form>
                    이름 : <input type="text" className="mname" />  <br/>
                    전화번호 :  <input type="text" className="mphone"  />  <br/>
                    <button onClick={ memberUpdate } type="button"> 수정 </button>
                </form>
            </>);
        }


