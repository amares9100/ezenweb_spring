import React from 'react';
import axios from 'axios'

export default function Find(props){

const findId = () =>{ console.log( 'findId open' )

            let mname = document.querySelector(".mname1").value;
            let mphone = document.querySelector(".mphone1").value;


    // ajax ---> axios 변환
    axios
        .get("http://localhost:8080/member/findId" , {
            params : {mname : mname,
                      mphone : mphone
                     }
                    } )
        .then( r => {  console.log( r );
            console.log(r.data);
           document.querySelector(".findid").innerHTML = r.data;
        })
        .catch( err => { console.log( err ) });
    }

    const findPw = () =>{ console.log( 'findPw open' )

               let memail = document.querySelector(".memail2").value;
               let mphone = document.querySelector(".mphone2").value;


        // ajax ---> axios 변환
        axios
            .get("http://localhost:8080/member/findPw" , {
                 params : {memail : memail,
                           mphone : mphone
                           }

            } )
            .then( r => {  console.log( r );
                 document.querySelector(".findpw").innerHTML = r.data;
            })
            .catch( err => { console.log( err ) });
        }


       return (<>

       <div>
                       <h3> 회원정보찾기[아이디 ] </h3>
                       <form>
                           이름 : <input type="text" className="mname1" />  <br/>
                           전화번호 :  <input type="text" className="mphone1" />  <br/>
                           <button onClick={ findId } type="button"> 아이디 찾기 </button>
                       </form>
                       <div class="findid"></div>
                       <h3> 회원정보찾기[비밀번호 ] </h3>
                        <form>
                            아이디[이메일] : <input type="text" className="memail2" /> <br/>
                            전화번호 :  <input type="text" className="mphone2" />  <br/>
                            <button onClick={ findPw} type="button"> 비밀번호 찾기 </button>
                            <div class="findpw"></div>
                        </form>
           </div>)
     </>)
}