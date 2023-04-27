import React,{ useState , useEffect } from 'react';
import axios from 'axios';
import {useParams} from 'react-router-dom'; // HTTP경로상의 매개변수 호출
import Container from '@mui/material/Container';
import Reply from './Reply';
import Rereplywrite from './Rereplywrite';

export default function View(props) {
    const params = useParams(); // 매개변수가 객체형태로 들어옴.
    console.log(params); // ex. {bno : '25'}
    console.log(params.bno); // ex. 25
    let [list , setList ] = useState({});
    let [ rows , setRows ] = useState( [] )
    let [rr , setRr]  =useState([])


     useEffect( ()=>{
            axios.get('/board/selectList',{ params : {bno : params.bno} })
                .then( r => { console.log(r);

                setList(r.data);
                setRows(r.data.replyEntityList);

                rows.map((r) =>{
                    setRr(r.rereplyDtoList);

                })

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

    const replyDelete=(e)=>{
            let rno = e.target.value;
            console.log("replyDelete : "+ rno)

             axios.delete('/board/replyDelete',{ params : {rno : rno} })
                       .then( r => { console.log(r);

                       if(r.data == true){
                       alert('삭제성공')
                       window.location.href="/board/List"

                       }

                       } )



        }

    const replyUpdate =(e)=>{
        let rno = e.target.value;
        {window.location.href="/board/ReplyUpdate?rno="+rno}
    }

    const rereplyWrite =(e)=>{
            let rno = e.target.value;
            {window.location.href="/board/Rereplywrite?rno="+rno}
        }

    const rereplyView =(e)=>{

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
        {rows.map((row , index ) => (

          row.mno === login.mno ? (
            <div>
            <span>내용 {row.rcontent} </span>/
            <button type="button" onClick={rereplyWrite} value={row.rno}>댓글쓰기</button>
            <button type="button" onClick={replyUpdate} value={row.rno}>수정</button>
            <button type="button" onClick={replyDelete} value={row.rno}>삭제</button>
            <button type="button" onClick={rereplyView} value={index}>댓글보기</button>
            <div className={row.rno}>

            </div>
            </div>

          ) : (
            <div>
            <span>내용 {row.rcontent} </span>
            <button type="button" onClick={rereplyWrite} value={row.rno}>댓글쓰기</button>
            <button type="button" onClick={rereplyView} value={index}>댓글보기</button>
            <div className={row.rno}>

            </div>
            </div>

          )
        ))}
        </div><br/>

        <Reply bno={params.bno}/>
        </Container>
    </>)

}