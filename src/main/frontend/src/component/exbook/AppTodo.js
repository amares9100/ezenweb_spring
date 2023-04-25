import React, { useState, useEffect } from 'react';
import Todo from './Todo';
import { Container, List, Paper } from '@mui/material';
import AddTodo from './AddTodo';
import axios from 'axios'; // npm install axios
import Pagination from '@mui/material/Pagination';
export default function AppTodo( props ) {

    const [items, setItems] = useState(
        [
        ]
    )
     let [ pageInfo , setPageInfo ] = useState( { 'page' : 1 } )
     let [totalPage , setTotalPage ] = useState(1);
     let [totalCount , setTotalCount ] = useState(1);

     const selectPage = (e) => {
        //console.log(e);
        //console.log(e.target);
        //console.log(e.target.value);
        //console.log(e.target.innerHTML); // 해당 버튼(태그)안에 있는 HTML 호출
        console.log(e.target.outerText); // 해당 버튼(태그) 밖에 있는 text출력

        pageInfo.page = e.target.outerText;
        setPageInfo({...pageInfo});


    }

    const getTodo = () => {
        axios.get("/todo" , { params : pageInfo } )
            .then( r => {
                console.log(r);
                setItems(r.data.todoDtoList);
                setTotalPage(r.data.totalPage);
                setTotalCount(r.data.totalCount);



                }
            );
    }

    useEffect(()=>{
        getTodo();
     }, [pageInfo])


    const addItem = (item) => {

        setItems([...items, item ]);

        console.log(item);
        axios.post("/todo", item).then(r=>{getTodo();});
    }

    // -------------------------------- deleting Item part --------------------------------
    const deleteItem = (item) => {
            console.log(items);
            console.log(item);
        const newItems = items.filter( e => e.id !== item.id );

        setItems( [...newItems] );


        // 배열/리스트.filter( (o) => {} );
        // 특징: map 문법에 조건 부여할 수 있음. 즉, 조건이 충족할 경우에만 객체를 return하도록 조작 가능!
        // JS 반복문 함수 모아보기
        // 1) forEach: 반복문 기능만 작동(return 반환 불가)
        // 2) map: 반복문 기능 수행하면서 return 반환처리 가능
        // 3) filter: 반복문 기능 수행하면서 조건부 return 반환 처리 가능

        axios.delete("/todo", {params: {id: item.id}}).then(r=>{getTodo();});
    }

    // -------------------------------- editing Item part --------------------------------
    const editItem = ()=>{
        setItems([...items]);
    };

    // -------------------------------- creating TodoItems part --------------------------------
    let TodoItems =
            <Paper style={ {margin: 16} }>
                <List>
                    {
                        items.map( ( i ) =>
                            <Todo item = { i } key = {i.id} editItem={editItem} deleteItem={deleteItem} />
                        )
                    }
                </List>
            </Paper>

    // -------------------------------- return part --------------------------------
    return(<>
        <div className="App">
            <Container maxWidth="md">
                <AddTodo addItem={addItem} />
                <div className="TodoList">{ TodoItems }</div>




            </Container>
        <div style={{display : 'flex' , justifyContent : 'center' }}>
            <Pagination count={totalPage} color="primary" onClick={selectPage}/>
        </div>
        </div>
    </>);

}