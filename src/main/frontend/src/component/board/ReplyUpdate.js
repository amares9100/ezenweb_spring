import React,{ useState , useEffect} from 'react';
import axios from 'axios';
import {useParams , useSearchParams} from 'react-router-dom'; // HTTP경로상의 매개변수 호출
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

export default function ReplyUpdate(props) {

      const [searchParams, setSearchParams] = useSearchParams();
      console.log(searchParams)
      console.log(searchParams.get("rno"))

    const rUpdate=()=>{
        let info ={
            rcontent : document.querySelector("#rcontent").value,
            rno : searchParams.get("rno")
        }

        axios.put('/board/replyUpdate', info)
                        .then( r => { console.log(r);
                        if(r.data == true){
                              alert('수정성공')
                              window.location.href="/board/List"

                              }

                        } )
    }



    return(<>
        <div>댓글수정</div>

         <Container>
                   <TextField fullWidth className="rcontent"   id="rcontent" label="수정할댓글내용" multiline rows={10} variant="standard" />
                   <Button variant="outlined" onClick={  rUpdate }> 수정하기 </Button>
         </Container>


    </>)
}