console.log("board.js")
// 1. 카테고리 등록
function setCategory(){  console.log("setCategory()")
    let cname = document.querySelector(".cname").value;
    $.ajax({ // ajax start
        url : "/board/category/write",
        method : "POST",
        data : JSON.stringify( {"cname" : cname} ),
        contentType : "application/json" ,
        success : (r)=>{
            console.log(r)
            if( r == true ) getCategory();
        } // success end
    }) // ajax end
} // setCategory end
// 2. 카테고리 모든 출력
getCategory()
function getCategory(){
    console.log("getCategory()")
    $.ajax({
        url : "/board/category/list",
        method : "get",
        success : (r)=>{
            console.log(r);
            let html = `<button onclick="selectorCno(0)" type="button">전체보기</button>`;
            for( let cno in r  ){
                console.log(" 키/필드 : " + cno);
                console.log(" 키/필드 에 저장된 값  : " + r[cno] );
                html += `<button onclick="selectorCno(${cno})" type="button">${ r[cno] }</button>`;
            } //for end
            document.querySelector('.categorylistbox').innerHTML = html;
        }
    })
}
// 3. 카테고리 선택
let selectCno = 0;
function selectorCno( cno ){
    console.log( cno +" 의 카테고리 선택");
    selectCno = cno;
    getBoard(cno);
}

function setBoard(){

     if(selectCno == 0){
        alert("카테고리를 선택해주세요.");
        return;
    }
    let info = {
        btitle : document.querySelector(".btitle").value,
        bcontent : document.querySelector(".bcontent").value,
        cno : selectCno
    }


    $.ajax({
    url : "/board/write",
    method : "post",
    data : JSON.stringify( info ),
    contentType : "application/json",
    success : (r)=>{
        console.log(r);
        document.querySelector(".btitle").value = '';
        document.querySelector(".bcontent").value = '';
        getBoard( selectCno ) // 현재 선택된 카테고리 기준으로 게시물들을 재출력
    }




    })

}
getBoard(0); // 0 : 전체보기
function getBoard(cno){
    selectCno = cno;

    $.ajax({
    url : "/board/list",
    method : "get",
    data : {"cno" : selectCno},
    success : (r)=>{
        console.log(r);
        let html=`
                    <tr>
                        <th> 번호 </th>
                        <th> 제목 </th>
                        <th> 작성자 </th>
                        <th> 작성일 </th>
                        <th> 조회수 </th>
                    </tr>
        `;
        r.forEach( (o)=>{
            html+=`
                     <tr>
                          <th> ${o.bno} </th>
                          <th onclick="detail(${o.bno})"> ${o.btitle} </th>
                          <th> ${o.mname} </th>
                          <th> ${o.bdate} </th>
                          <th> ${o.bview} </th>
                     </tr>
            `

        });



        document.querySelector(".boardlistbox").innerHTML = html;
    }
 })
}

// 내글보기
function myboards(){

    $.ajax({
    url : "/board/myboards",
    method : "get",
    success : (r)=>{
        console.log(r);
     let html=`
                        <tr>
                            <th> 번호 </th>
                            <th> 제목 </th>
                            <th> 작성자 </th>
                            <th> 작성일 </th>
                            <th> 조회수 </th>
                        </tr>
            `;

      r.forEach( (o)=>{
                 html+=`
                          <tr>
                               <th> ${o.bno} </th>
                               <th onclick="detail(${o.bno})"> ${o.btitle} </th>
                               <th> ${o.mname} </th>
                               <th> ${o.bdate} </th>
                               <th> ${o.bview} </th>
                          </tr>
                 `

             });
             document.querySelector(".boardlistbox").innerHTML = html;

    }


    })


}

function detail(bno){
    console.log("클릭했다" + bno)

     $.ajax({
        url : "/board/detail",
        method : "get",
        data : {"bno" : bno},
        success : (r)=>{
            console.log(r);
        let html=`
                            <tr>
                                <th> 번호 </th>
                                <th> 제목 </th>
                                <th> 내용 </th>
                                <th> 작성자 </th>
                                <th> 작성일 </th>
                                <th> 조회수 </th>
                                <th> 비고 </th>
                            </tr>
                `;
                r.forEach( (o)=>{
                    html+=`
                             <tr>
                                  <th> ${o.bno} </th>
                                  <th> ${o.btitle} </th>
                                  <th> ${o.bcontent} </th>
                                  <th> ${o.mname} </th>
                                  <th> ${o.bdate} </th>
                                  <th> ${o.bview} </th>
                                  <th> <button onclick="boarddelete(${o.bno})" type="button"> 글삭제</button></th>
                             </tr>
                    `

                });
                document.querySelector(".boardbox").innerHTML = html;
        }
     })

}


function boarddelete(bno){
    console.log("delete bno" + bno);
    $.ajax({
     url : "/board/boarddelete",
         method : "delete",
         data : {"bno" : bno},
         success : (r)=>{
        console.log("삭제 리턴값 : " + r);
            }
    })

}

/*
    해당 변수의 자료형 확인 Prototype
    Array : forEach() 가능
        { object , object , object  }
    object : forEach() 불가능 --->  for( let key in object ){ } : 객체내 key를 하나씩 출력
        {
            필드명 : 값 ,
            필드명 : 값 ,
            필드명 : 값
        }
        object[필드명] : 해당 필드의 값 호출
*/