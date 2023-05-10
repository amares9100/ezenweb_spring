import React,{ useState , useEffect , useRef } from 'react';
import axios from 'axios';
import ProductTable from './ProductTable';
import Container from '@mui/material/Container';
import Box from '@mui/material/Box';
import Tab from '@mui/material/Tab';
import TabContext from '@mui/lab/TabContext';
import TabList from '@mui/lab/TabList';
import TabPanel from '@mui/lab/TabPanel';

export default function ProductWrite( props ) {
    const WriteForm = useRef(null);

    const onWriteHandler =()=>{
        console.log(WriteForm.current)
        console.log(new FormData(WriteForm.current));
        const writeFormData = new FormData(WriteForm.current);

        axios.post('/product' , writeFormData)
            .then((r)=>{
            if(r.data == true){
                alert('등록성공')
            }

            })

    }


    return (<>
            <form ref={WriteForm}>
            제품명 : <input type="text" name="pname" />
            제품가격 : <input type="text" name="price" />
            제품카테고리 : <input type="text" name="pcategory" />
            제품설명 : <input type="text" name="pcommnet" />
            제품제조사 : <input type="text" name="pmanufacturer" />
            제품초기상태 : <input type="text" name="pstate" />
            제품제고 : <input type="text" name="pstock" />
            제품이미지 : <input
                        type="file" multiple
                        accept="image/gif,image/png,image/jpeg"
                        name="pimgs"
                        />
            <button type="button" onClick={onWriteHandler}>제품등록</button>
            </form>


    </>)
}