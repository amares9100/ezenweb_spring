// 컴포넌트(함수) 생성하기
    // 1. 리엑트 라이브러리 필요 : import React from 'react'
import React from 'react'
    // 2. 컴포넌트(함수) 선언 - 주의점 : 첫글자는 대문자로
                                  //: 랜더링[DOM] 할 부분 return에 포함
                           // return 주의할점
                           //       : (시작) 끝 구성
                           //       : <div>혹은 <>전체 감싸는 태그 필수
                           //       : function 컴포넌트명(){return (<div> </div>) }
                           //       : 해당 컴포넌트 리턴해주는 명령어 export default 컴포넌트명
function Book(){
    return (<div> 처음으로 만든 리액트 컴포넌트</div>)

}

export default Book