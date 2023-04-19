import React , {useState} from 'react';
import {Button , Grid , TextField } from "@mui/material"

export default function AddTodo(props) {
    const [item , setItem ] = useState( {title : ""})

    const onInputChange = (e) =>{
        setItem ({title : e.target.value});
        console.log(item);
    }
    console.log("AddTodo props: " + props) // 전달받은 함수는 오브젝트로 넘어옴
    console.log("AddTodo props.addItem: " + props.addItem) // AppTodo의 함수 전체가 들어있음.
    const addItem = props.addItem // AppTodo에서 전달받은 함수를 저장

        // + 버튼 클릭했을때 전달받은 함수에 입력받은 데이터를 대입 ->> AddTodo에서 입력된 값을 AppTodo에서 가져가고 실행됨
        const onButtonClick = () => {
            addItem(item); // 입력받은 데이터를 addtodo컴포넌트에게 매개변수 대입
            setItem({title : ""});
        }

        const enterKeyEventHandler =(e) =>{
            if(e.key == 'Enter'){
                onButtonClick();
            }

        }
    return (<>
        <Grid container style={{marginTop : 20}}>
            <Grid xs={11} md={11} item style={{paddingRight:16}}>
                <TextField placeholder="여기에 Todo작성 " fullWidth onChange={onInputChange} value={item.title} onKeyPress={enterKeyEventHandler} />

            </Grid>
            <Grid xs={1} md={1} item>
                <Button fullWidth style={{height : "100%"}} color="secondary" variant="outlined" onClick={onButtonClick}>
                    +

                </Button>
            </Grid>
        </Grid>
    </>
    )
}