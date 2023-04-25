import React, { useState, useEffect }  from 'react';
import { ListItem, ListItemText, InputBase, Checkbox, ListItemSecondaryAction, IconButton } from '@mui/material';
import DeleteOutlined from "@mui/icons-material/DeleteOutlined";
import axios from 'axios';

export default function Todo( props ) {
        // console.log(location.origin);
        console.log(props);

    const [ item, setItem ] = useState( props.item );

    const deleteItem = props.deleteItem;


    const deleteEventHandler = ()=>{
        deleteItem(item);
    }


    const [readOnly, setReadOnly ] = useState(true);

    const turnOffReadOnly = () => {
        setReadOnly(false); // readOnly = true(수정불가), false(수정가능)
    }

    // turnOnReadOnly 메소드 작성 (기능: Enter Key 작동 시, 읽기모드로 전환(수정 불가))
    const turnOnRead = (e) => {
        if(e.key === "Enter"){
            setReadOnly(true);

            axios.put("/todo", item)
            .then(r=> {
                editItem();
            });
        }
    }


    const editItem = props.editItem;
    const editEventHandler = (e) => { // editEventHandler 생성(기능: 제목 수정)
            console.log(e);
        item.title = e.target.value;
        editItem();
    }
    const checkboxEventHandler = (e) => { // checkboxEventHandler 생성(기능: 체크 박스 상태 변화)
        item.done = e.target.checked;
        axios.put("/todo", item).then(r=>{ editItem();
        })

    }

    // -------------------------------- return part --------------------------------
    return(<>
        <ListItem>

            <Checkbox checked={item.done} onChange={checkboxEventHandler} />
            <ListItemText>
                <InputBase inputProps = {{readOnly:readOnly}}
                    onClick={turnOffReadOnly}
                    onKeyDown={turnOnRead}
                    onChange={editEventHandler}
                    type="text"
                    id={ item.id }
                    name={ item.id }
                    value={ item.title }
                    multiline={ true }
                    fullWidth={ true }
                />
            </ListItemText>

            <ListItemSecondaryAction>
                <IconButton onClick={deleteEventHandler}>
                    <DeleteOutlined />
                </IconButton>
            </ListItemSecondaryAction>

        </ListItem>
    </>);
    // 출력 내용: ListItem 구역 안에 Checkbox, Text 정보, Delete 버튼 (MUI 사용함)
}