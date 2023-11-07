// export : JS 모듈에서 함수, 객체, 원시 값을 내보낼 때 사용
// 내보낸 값은 다른 프로그램에서 import 문으로 가져가 사용 가능
import axios from "axios";

export const API_SERVER_HOST = "http://localhost:8080"

const prefix = `${API_SERVER_HOST}/api/todo`

export const getOne = async (tno) => {
    const res = await axios.get(`${prefix}/${tno}`)

    return res.data
}

export const getList = async  (pageParam) => {

    const {page, size} = pageParam

    const res = await axios.get(`${prefix}/list`, {params:{page, size}})

    return res.data
}

// promise
/* - 자바스크립트 비동기 처리에 사용되는 객체
   - 서버에서 받아온 데이터를 화면에 표시할 때 사용
*
* */

/* 유즈이펙트?
* - 비동기 통신시 어떤 조건에서만 호출하는 것
* */