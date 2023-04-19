import React from 'react'
import Component from './Comment'
export default function  CommentList(props){

    let r = [
    {name : "유재석" , comment : "안녕하세요"},
    {name : "강호동" , comment : "핼로"},
    {name : "신동엽" , comment : "안녕"}
    ]
    // Map = 리턴가능 / forEach = 리턴 불가능
    return (<>
        <div>
            { r.map((c) =>{
                return (<Component name={c.name} comment={c.comment} />)

             })

            }

        </div>
    </>);
}