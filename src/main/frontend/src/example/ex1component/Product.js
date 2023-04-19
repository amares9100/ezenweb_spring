import React from 'react'

/*컴포넌트 기본형
export default function  컴포넌트명(){
    return (<> </>);
}
*/
// props : properties 약자  : 매개변수를 JSON형태로 변환
export default function  Product(props){

    console.log(props)

    return (<>
        <div>
            <h5> 제품명 : {props.name} </h5>
            <h6> 가격 :  {props.price} </h6>
        </div>

    </>);
}
