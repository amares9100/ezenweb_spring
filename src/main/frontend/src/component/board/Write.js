import React,{ useState , useEffect } from 'react';
import axios from 'axios';

import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

import CategoryList from './CategoryList'

export default function Write( props ) {

    // 1. 게시물 쓰기
    const setBoard = () => {
        let info = {
            btitle : document.querySelector("#btitle").value,
            bcontent : document.querySelector("#bcontent").value,
            cno : cno
        }
        console.log( info );

        axios.post( '/board/write' , info )
            .then( r => { console.log(r);
            if(r.data == 1){
            alert( '카테고리 선택후 쓰기가능합니다. [ 전체보기 제외 ]')
           }
            else if(r.data ==2 ) {
                alert('로그인후 작성 가능합니다.')
            }
            else if(r.data ==3){
                alert('작성실패 관리자에게 문의하세요')
            }
            else if(r.data ==4){
                alert('작성성공')
                window.location.href="/"
             }


            })
    }

    // 카테고리 선택
    let [cno , setCno] = useState(0);

    const categoryChange = (cno) => {

    setCno(cno);

    }

    return(<>
        <Container>
            <CategoryList categoryChange={categoryChange} />
            <TextField fullWidth className="btitle"     id="btitle"  label="제목" variant="standard" />
            <TextField fullWidth className="bcontent"   id="bcontent" label="내용" multiline rows={10} variant="standard" />
            <Button variant="outlined" onClick={  setBoard }> 등록 </Button>
            <Button variant="outlined"> 취소 </Button>
        </Container>
    </>)
}