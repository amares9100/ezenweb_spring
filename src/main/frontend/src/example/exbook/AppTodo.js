import React , { useState } from 'react'
import Todo from './Todo'
import styles from "../../App.css"
import {List , Paper , Container} from "@mui/material"
import AddTodo from "./AddTodo"

export default function AppTodo(props) {

    const [items , setItems] = useState(
        [

        ]
    )
        // addItem 함수 만들기
        const addItem = (item) =>{ // 함수로부터 매개변수로 전달받은 item
                item.id = "ID-"+item.length
                item.done = false;
                setItems([...items , item]); // 기존 상태 items에 item을 추가
                }



        let TodoItems =

            <Paper style={{margin : 16}}>
                  <List>
                         {items.map((i)=>
                               <Todo item={i} key={i.id} />
                         )}
                  </List>
             </Paper>

        // addItem함수를 addTodo로 보내기
    return (<>
            <div className="App">
            <Container maxWidth="md">
                                <AddTodo addItem={addItem} />
                                {TodoItems}
            </Container>
        </div>
         </>
    )
}