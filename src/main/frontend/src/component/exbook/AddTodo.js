import React, { useState } from 'react';
import { Button, Grid, TextField } from '@mui/material';
import axios from 'axios'; // npm install axios
export default function AddTodo( props ) {

    const [ item, setItem ] = useState({title: ""}, {content: ""});

    const onInputChange = (e) => {
            // console.log(e);
            // console.log(e.target);
        setItem({title: e.target.value});
            console.log(item);
    }

    const addItem = props.addItem;


    // onButtonClick 메소드 생성(기능: addItem메소드에 매개변수로 item 전달 후 title값 공백으로 초기화)
    const onButtonClick = ()=>{
        addItem(item);
        setItem({title:""})
    }

    // enterKeyEventHandler 메소드 생성 (기능: enter key 눌리면, onButtonClick 메소드 작동)
    const enterKeyEventHandler = (e) => {
        if(e.key==='Enter'){
            onButtonClick();
        }
    }


    return(<>
        <Grid container style={{marginTop:20}}>
           <Grid xs={11} md={11} item style={{paddingRight:16}}>
                <TextField
                    placeholder="Todo 추가할 내용"
                    fullWidth
                    onChange={onInputChange}
                    onKeyPress={enterKeyEventHandler}
                    value={item.title}
                />
           </Grid>
            <Grid xs={1} md={1} item>
                <Button
                    fullWidth
                    style ={{height:'100%'}}
                    color="secondary"
                    variant="outlined"
                    onClick={onButtonClick}
                >
                +
                </Button>
            </Grid>
        </Grid>
    </>);

}