import React,{ useState , useEffect } from 'react';
import axios from 'axios';
import {useParams , useSearchParams} from 'react-router-dom'; // HTTP경로상의 매개변수 호출
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';


export default function Reply(props){
    let user = JSON.parse( sessionStorage.getItem("login_token") )
    // 인수 확인
    console.log("리플 확인 : " + props.bno);
    console.log(user.mno)


    const replyWrite =()=>{

        let info = {
            bno : props.bno,
            mno : user.mno,
            rcontent : document.querySelector("#rcontent").value
        }
        console.log("댓글info : " + info)
        axios.post('/board/replyWrite', info)
              .then( r => { console.log(r);

              if(r.data == true){
              alert('수정성공')

              }

              } )


    }





    return (<>
    <h3>댓글작성</h3>

                <Container>

                      <TextField fullWidth className="rcontent"   id="rcontent" label="댓내용" multiline rows={10} variant="standard" />
                      <Button variant="outlined" onClick={replyWrite}> 댓글작성 </Button>

                </Container>

    </>)
}