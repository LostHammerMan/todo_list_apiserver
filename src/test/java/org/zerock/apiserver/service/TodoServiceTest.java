package org.zerock.apiserver.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PagingRequestDto;
import org.zerock.apiserver.dto.TodoDto;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class TodoServiceTest {

    @Autowired
    TodoService todoService;

    // 조회 테스트
    @Test
    public void getTest(){
        Long tno = 50L;
        todoService.get(tno);
        log.info(" todoService.get(tno) = {}",  todoService.get(tno));
    }

    // 등록 테스트
    @Test
    public void registerTest(){

        TodoDto todoDto  = TodoDto.builder()
                .title("titleExample")
                .content("example!!!!!")
                .dueDate(LocalDate.of(2024, 12, 31))
                .build();

        Long result = todoService.register(todoDto);
        log.info("result = {}", result);
    }

    // 수정 테스트
    @Test
    public void modifyTest(){
       TodoDto findTodo = todoService.get(56L);
       findTodo.setTitle("Lost Ark");
       findTodo.setContent("Destroyer!");
       findTodo.setComplete(true);

        todoService.modify(findTodo);

        log.info("findTodo = {}", findTodo);
    }

    // 페이징 테스트
    @Test
    public void getListTest(){
        PagingRequestDto pagingRequestDto = PagingRequestDto.builder().page(5).build();

        log.info("\t todoService.getList(pagingRequestDto) = {}", todoService.getList(pagingRequestDto));
    }
}
