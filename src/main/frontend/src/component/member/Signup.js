import React , { useState , useEffect } from 'react'
import axios from 'axios'
export default function Signup( props ) {
    //function onSignup(){} --> 변수형 익명함수 변환
    //unction onSignup(){} --> const 변수 = () => {}
    const onSignup = () =>{ console.log( 'onSignup open' )
        let info = {
            memail : document.querySelector(".memail").value,
            mpassword : document.querySelector(".mpassword").value,
            mname : document.querySelector(".mname").value,
            mphone : document.querySelector(".mphone").value
        }
        console.log( info )
    // ajax ---> axios 변환
    axios
        .post("/member/info" , info )
        .then( r => {  console.log( r );
            if( r.data == true  ){
                alert('회원가입 성공');window.location.href="/member/Login"; // window.location.href="이동할 경로";
            }else{
                alert('회원가입 실패 [ 관리자에게 문의 ]');
            }
        })
        .catch( err => { console.log( err ) });
    }
    let [memailMsg, setMemailMsg] = useState();

    const idCheck = (e) =>{ console.log( 'idCheck open' )

                let memail = document.querySelector(".memail").value;



        // ajax ---> axios 변환
        axios
            .get("/member/idCheck" , {params: {memail : e.target.value}})
            .then( r => {

        if(r.data == true){ setMemailMsg('사용중인 이메일입니다.')}
        else{setMemailMsg('사용가능한아이디입니다.')}

            })
            .catch( err => { console.log( err ) });
        }

        let [mphoneMsg, setMphoneMsg] = useState();

            const mphoneCheck = (e) =>{ console.log( 'mphoneCheck open' )

                        let mphone = document.querySelector(".mphone").value;



                // ajax ---> axios 변환
                axios
                    .get("/member/phoneCheck" , {params: {mphone : e.target.value}})
                    .then( r => {

                if(r.data == true){ setMphoneMsg('사용중인 전화번호입니다.')}
                else{setMphoneMsg('사용가능한전화번호입니다.')}

                    })
                    .catch( err => { console.log( err ) });
                }





    return(<div>
                <h3> 회원가입 페이지 </h3>
                <form>
                    아이디[이메일] : <input type="text" className="memail" onChange={idCheck} /> {memailMsg}<br/>

                    비밀번호 : <input type="text" className="mpassword" />  <br/>
                    이름 : <input type="text" className="mname" />  <br/>
                    전화번호 :  <input type="text" className="mphone" onChange={mphoneCheck} /> {mphoneMsg} <br/>
                    <button onClick={ onSignup } type="button"> 가입 </button>
                </form>
    </div>)
}
/*
    HTML ---> JSX
        1. <> </>
        2. class -> clasName
        3. style -> style={{ }}
        4. 카멜표기법 :
            onclick -> onClick
            margin-top -> marginTop
*/
