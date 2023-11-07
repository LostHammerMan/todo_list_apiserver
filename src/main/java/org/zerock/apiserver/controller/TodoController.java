package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.PagingRequestDto;
import org.zerock.apiserver.dto.PagingResponseDto;
import org.zerock.apiserver.dto.TodoDto;
import org.zerock.apiserver.service.TodoService;

import javax.sql.DataSource;
import java.util.Map;

// 화면 없이 api 만 만드는 경우
// view 가 필요 없으니 @RestController 로 작성
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    // 데이터가 항상성을 가지는 경우 @PathVariable
    @GetMapping("/{tno}")
    public TodoDto get(@PathVariable("tno") Long tno){
        return todoService.get(tno);
    }

    // 데이터가 계속 변하는 경우 QueryString 사용

    @GetMapping("/list")
    public PagingResponseDto<TodoDto> list(PagingRequestDto pagingRequestDto){
        log.info("list called......");
        log.info("pagingRequestDto = {}", pagingRequestDto);
        return todoService.getList(pagingRequestDto);
    }

    // 등록
    // JSON 전송이므로 Map type 으로 반환타입 결정
    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDto dto){
        log.info("todoDto = {}", dto);
        Long tno = todoService.register(dto);
        return Map.of("tno", tno);
    }

    // 수정
    @PutMapping("/{tno}")
    public Map<String, String> modify(@PathVariable Long tno,
                                      @RequestBody TodoDto todoDto){
        todoDto.setTno(tno);
        todoService.modify(todoDto);

        return Map.of("result", "success");
    }

    // 삭제
    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable Long tno){
        todoService.remove(tno);
        return Map.of("result", "success");
    }
}
