import React,{ useState , useEffect} from 'react';
import axios from 'axios';
import {useParams , useSearchParams} from 'react-router-dom'; // HTTP경로상의 매개변수 호출
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import View from './View';

export default function RereplyView(props) {
    const [login , setLogin ] = useState(JSON.parse( sessionStorage.getItem("login_token") ))
    const params = useParams(); // 매개변수가 객체형태로 들어옴.
    let [rereply , setRereply ] = useState(props.rereplyDtoList);


   const onRereplyUpdate = (e) =>{
    let rrno = e.target.value;
    console.log(rrno)
    let rrcontent = document.querySelector(".rrcontent").value;

    let info = {
        rrno : rrno ,
        rrcontent : rrcontent
    }

    axios.put('/board/rereplyUpdate', info)
                        .then( r => { console.log(r);

                         if(r.data == true){
                          alert('수정 성공');
                          window.location.href="/board/view/"+params.bno
                          }

                         })


   }

   const onRereplyDelete = (e) =>{
      let rrno = e.target.value;
      console.log(rrno)

      axios.delete('/board/rereplyDelete',{ params : {rrno : rrno} })
                      .then( r => { console.log(r);
                      if(r.data == true){
                      alert('삭제 성공');
                      window.location.href="/board/view/"+params.bno
                      }
                      } )

   }


    return (
      <div>
        {rereply.map((re) =>
          re.mno === login.mno ? (
            <div>
              --> {re.rrcontent} | <input type="text" className="rrcontent" />
              <button onClick={onRereplyUpdate} type="button" value={re.rrno}>수정</button>
              <button onClick={onRereplyDelete} type="button" value={re.rrno}>삭제</button>

            </div>
          ) : (
            <div>--> {re.rrcontent}</div>
          )
        )}
      </div>
    );
}