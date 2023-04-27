import React,{ useState , useEffect} from 'react';
import axios from 'axios';
import {useParams , useSearchParams} from 'react-router-dom'; // HTTP경로상의 매개변수 호출
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import CategoryList from './CategoryList'

export default function BoardUpdate(props){

        const [searchParams, setSearchParams] = useSearchParams();
        const [cno , setCno] = useState(0);
        console.log(searchParams.get("bno"))



        const Update=()=>{
            console.log("Update")

            let info ={
            bno : searchParams.get("bno"),
            btitle : document.querySelector("#btitle").value,
            bcontent : document.querySelector("#bcontent").value,
            cno : cno

            }
            console.log(info)

            axios.put('/board/listUpdate', info)
                            .then( r => { console.log(r);

                             if(r.data == true){
                              alert('수정성공')

                             }

                            } )


    }

     const categoryChange = (cno) => {

        setCno(cno);

        }



        return (<>
            <div>업데이트</div>

            <Container>
                        <CategoryList categoryChange={categoryChange} />
                        <TextField fullWidth className="btitle"     id="btitle"  label="제목" variant="standard" />
                        <TextField fullWidth className="bcontent"   id="bcontent" label="내용" multiline rows={10} variant="standard" />
                        <Button variant="outlined" onClick={  Update }> 수정하기 </Button>
                     </Container>
        </>)
}