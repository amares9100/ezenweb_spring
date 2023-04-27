import React,{ useState , useEffect} from 'react';
import axios from 'axios';
import {useParams , useSearchParams} from 'react-router-dom'; // HTTP경로상의 매개변수 호출
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

export default function Rereplywrite(props) {
     const [searchParams, setSearchParams] = useSearchParams();
     console.log(searchParams)
     console.log(searchParams.get("rno"))

    let user = JSON.parse( sessionStorage.getItem("login_token") )
    const rereplyWrite =()=>{

            let info = {
                rno : searchParams.get("rno"),
                mno : user.mno,
                rrcontent : document.querySelector("#rrcontent").value
            }
            console.log("대댓글info : " + info)
            axios.post('/board/rereplyWrite', info)
                  .then( r => { console.log(r);

                  if(r.data == true){
                  alert('작성 성공')


                  }

                  } )


        }


    return (<>

        <div>대댓글</div>

                    <Container>


                                <TextField fullWidth className="rrcontent"   id="rrcontent" label="대댓글내용" multiline rows={10} variant="standard" />
                                <Button variant="outlined" onClick={rereplyWrite}> 작성하기 </Button>
                             </Container>



    </>)

}