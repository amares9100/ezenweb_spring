import React from 'react'

import styles from './Comment.css'
// img 가져오기
import logos from '../../logo.svg'
export default function  Comment(props){
    return (<>
        <div className="wrapper">
            <div>
                <img src={logos} className="logoimg"/>
            </div>
            <div className="contentContainer">
                <div className="nameText"> {props.name}</div>
                <div className="commentText"> {props.comment} </div>
            </div>
        </div>
    </>);
}