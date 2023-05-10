import React from 'react';
import { BrowserRouter , Routes , Route }
    from "react-router-dom" // npm install react-router-dom
import Header from "./Header"
import Footer from "./Footer"
import Main from "./Main"
import Login from "./member/Login"
import Signup from "./member/Signup"
import Find from "./member/Find"
import List from "./board/List"
import DashBoard from "./admin/DashBoard"
import Write from "./board/Write"
import Update from "./member/Update"
import Secession from "./member/Secession"
import View from "./board/View"
import BoardUpdate from "./board/BoardUpdate"
import ReplyUpdate from "./board/ReplyUpdate"
import Rereplywrite from "./board/Rereplywrite"
import AppTodo from "./exbook/AppTodo"
import Chatting from "./board/Chatting"

export default function Index( props ) {
    return ( <>
        <BrowserRouter>
            <Header />
            <Routes >
                <Route path="/" element = { <Main/> } />
                <Route path="/member/Login" element = { <Login/> } />
                <Route path="/member/Signup" element = { <Signup/> } />
                <Route path="/member/Find" element = { <Find/> } />
                <Route path="/board/List" element = { <List/> } />
                <Route path="/admin/DashBoard" element = { <DashBoard/> } />
                <Route path="/board/Write" element = { <Write/> } />
                <Route path="/member/Update" element = { <Update/> } />
                <Route path="/exbook/AppTodo" element = { <AppTodo/> } />
                <Route path="/member/Secession" element = { <Secession/> } />
                <Route path="/board/View/:bno" element = { <View/> } />
                <Route path="/board/BoardUpdate" element = { <BoardUpdate/> } />
                <Route path="/board/ReplyUpdate" element = { <ReplyUpdate/> } />
                <Route path="/board/Rereplywrite" element = { <Rereplywrite/> } />
                <Route path="/board/Chatting" element = { <Chatting/> } />
            </Routes>
            <Footer />
        </BrowserRouter>
    </> )
}

/*
     react-router-dom 다양한 라우터 컴포넌트 제공
     1. <BrowserRouter> : 가상 URL 관리[  브라우저 URL 동기화 ]
     2. <Routes>        : 가장 적합한 <Route> 컴포넌트를 검토하고 찾는다.
            요청된 path에 적합한 <Route> 찾아서 <Routes> 범위내 렌더링
     3. <Route>         : 실제 URL 경로 지정해주는 컴포넌트
        <Route path="login" element={ <Login /> } />
            http://localhost:3000/login     get 요청시 Login 컴포넌트 반환

*/
