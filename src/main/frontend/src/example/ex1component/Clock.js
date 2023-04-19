import React from 'react'


export default function  Clock(props) {
    // 함수안에서 js 문법은 자유적으로 작성가능
    // 함수안에 return () 안에서 js문법은 {}처리


    return (<>
        <div>
            <h3> 리엑트 시계 </h3>
            <h4> 현재 시간 : { new Date().toLocaleTimeString() }</h4>
        </div>
    </>);
}
