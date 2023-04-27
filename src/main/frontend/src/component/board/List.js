import React,{ useState , useEffect } from 'react';
import axios from 'axios';
/* ---------table mui -------- */
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Pagination from '@mui/material/Pagination';
/* ---------------------------*/
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';


import CategoryList from './CategoryList';



export default function List( props ) {



    // 1. 요청한 게시물 정보를 가지고 있는 리스트 변수[ 상태 관리변수 ]
    let [ rows , setRows ] = useState( [] )
    let [ pageInfo , setPageInfo ] = useState( { 'cno' : 0 , 'page' : 1 , 'key' : '' , 'keyword' : ''} )
    let [totalPage , setTotalPage ] = useState(1);
    let [totalCount , setTotalCount ] = useState(1);
    // 2. 서버에게 요청하기 [ 컴포넌트가 처음 생성 되었을때 ]
    useEffect( ()=>{
        let user = JSON.parse( sessionStorage.getItem("login_token") )
        if(user == null){
                    alert("로그인해주세요")
                    window.location.href="/member/Login"
                }

        axios.get('/board/list',{ params : pageInfo })
            .then( r => { console.log(r);
            setRows( r.data.boardDtoList );
            setTotalPage(r.data.totalPage);
            setTotalCount(r.data.totalCount);


            } )
            .catch( err => { console.log(err); })
    } , [pageInfo] ) // cno 변경될때마다 해당 useEffect 실행된다.

    // useEffect( ()=>{}  )             : 생성 , 업데이트
    // useEffect( ()=>{} , [] )         : 생성될때 1번
    // useEffect( ()=>{} , [변수] )     : 생성 , 해당 변수가 업데이트 될때마다 새 렌더링

    // 3. 카테고리 변경
    const categoryChange = ( cno ) => {
        pageInfo.cno =cno;
        pageInfo.key = '';
        pageInfo.keyword = '';
        setPageInfo({...pageInfo});
        }


    // 4. 페이징 번호
    const selectPage = (event , value) => {
        //console.log(e);
        //console.log(e.target);
        //console.log(e.target.value);
        //console.log(e.target.innerHTML); // 해당 버튼(태그)안에 있는 HTML 호출
        //console.log(event.target.outerText); // 해당 버튼(태그) 밖에 있는 text출력
        console.log(value); //
        pageInfo.page = value;
        setPageInfo({...pageInfo});


    }

    const onSearch =()=>{
        pageInfo.key = document.querySelector(".key").value;
        pageInfo.keyword = document.querySelector(".keyword").value;
        pageInfo.page = 1
        document.querySelector(".keyword").value = '';
        setPageInfo({...pageInfo});
    }




    return (
    <Container>
        <div>페이지 확인 : {pageInfo.page} / 게시물수 : {totalCount} </div>
        <div style={{ display:'flex' , justifyContent : 'space-between' , alignItems : 'center' }}>
            <CategoryList categoryChange = { categoryChange } />
            <a href="/board/Write"><Button variant="outlined"> 게시물 작성 </Button></a>
        </div>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell align="center" style={{ width:'8%' }}>번호</TableCell>
                <TableCell align="center" style={{ width:'60%' }}>제목</TableCell>
                <TableCell align="center" style={{ width:'10%' }}>작성자</TableCell>
                <TableCell align="center" style={{ width:'10%' }}>작성일</TableCell>
                <TableCell align="center" style={{ width:'10%' }}>조회수</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row) => (
                <TableRow  key={row.name}   sx={{ '&:last-child td, &:last-child th': { border: 0 } }}  >
                  <TableCell align="center" component="th" scope="row"> {row.bno} </TableCell>
                  <TableCell align="left"><a href={'/board/View/'+row.bno}>{row.btitle}</a></TableCell>
                  <TableCell align="center">{row.mname}</TableCell>
                  <TableCell align="center">{row.bdate}</TableCell>
                  <TableCell align="center">{row.bview}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <div style={{display : 'flex' , justifyContent : 'center' }}>
            <Pagination count={totalPage}  color="primary" onChange={selectPage}/>
        </div>
           <div>
                <select className="key">
                    <option value="btitle">제목</option>
                    <option value="bcontent">내용</option>
                </select>
                <input type="text" className="keyword" />
                <button type="button" onClick={onSearch}> 검색 </button>
           </div>
    </Container>
    );
}