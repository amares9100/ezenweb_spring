import React,{ useState , useEffect } from 'react';
import axios from 'axios';
import {useParams} from 'react-router-dom'; // HTTP경로상의 매개변수 호출
import Container from '@mui/material/Container';
import Reply from './Reply';

export default function View(props) {
    const params = useParams(); // 매개변수가 객체형태로 들어옴.
    console.log(params); // ex. {bno : '25'}
    console.log(params.bno); // ex. 25
    let [list , setList ] = useState({});
    let [ rows , setRows ] = useState( [] )
     useEffect( ()=>{
            axios.get('/board/selectList',{ params : {bno : params.bno} })
                .then( r => { console.log(r);

                setList(r.data);
                setRows(r.data.replyEntityList);

                } )
                .catch( err => { console.log(err); })
        } , [] )






    const listDelete=()=>{
           console.log("listDelete")
         axios.delete('/board/selectList/delete',{ params : {bno : params.bno} })
                    .then( r => { console.log(r);

                     if(r.data == true){
                     alert('삭제성공')
                     window.location.href="/board/List"

                     }

                    } )
             }

    const listUpdate =()=> {window.location.href="/board/BoardUpdate?bno="+list.bno}
    const [login , setLogin ] = useState(JSON.parse( sessionStorage.getItem("login_token") ))
    const btnBox = login != null && login.mno == list.mno ? <div><button type="button" onClick={listUpdate}> 수정</button><button type="button" onClick={listDelete}> 삭제 </button></div> : <div></div>;




    return (<>
        <Container>

        <h3>View</h3>
        <div>카테고리 : {list.cname}</div>
        <div>작성자 : {list.mname}</div>
        <div>제목 : {list.btitle}   </div>
        <div>내용 : {list.bcontent}</div>
        <div>작성날짜 : {list.bdate}</div>
        <div>{btnBox}</div><br/>
        <h3>댓글란</h3>
        <div>
         {rows.map((row) => (
            <div>번호{row.rno} / 내용{row.rcontent}</div>
         ))}
        </div><br/>
        <Reply bno={params.bno}/>
        </Container>
    </>)

}