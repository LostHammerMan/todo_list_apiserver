package org.zerock.apiserver.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestExecutionListeners;
import org.zerock.apiserver.domain.Todo;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository repository;



    @Test
    public void test1(){
        Assertions.assertNotNull(repository);
        log.info("repository.getClass().getName() = {}" , repository.getClass().getName());
    }

    // insert test
    @Test
    public void testInsert(){

        for (int i=0; i<50; i++){
            Todo todoList = Todo.builder()
                    .title("Title" + i)
                    .content("test" + i)
                    .dueDate(LocalDate.of(2023, 12, 10))
                    .build();
            repository.save(todoList);
        }


        Todo todo = Todo.builder()
                        .title("Title1")
                        .content("test1")
                        .dueDate(LocalDate.of(2023, 12, 10))
                        .build();

        Todo result = repository.save(todo);
        log.info("result = {}", result);
    }

    // read  test
    @Test
    public void readTest(){
        // null 체크를 위해  Optional 타입 사용
        Optional<Todo> findTodo = repository.findById(1L);
        Todo todo = findTodo.orElseThrow();
        log.info("Todo = {}", todo);
    }

    // update test
    @Test
    public void updateTest(){

        // 먼저 로딩 하고 엔티티 객체를 변경
        Optional<Todo> findTodo = repository.findById(1L);
        Todo todo = findTodo.orElseThrow();

        todo.updateTodo(
                "update Title", "update Content", true
                , LocalDate.of(2024, 12, 31)
        );
        repository.save(todo);
        log.info("todo = {}", todo);
    }

    // paging test
    @Test
    public void pagingTest(){
        // 페이지 번호는 0부터 시작에 유의
        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("tno").descending());

        Page<Todo> result = repository.findAll(pageable);

        log.info("pagingResult.getTotalElement = {}", result.getTotalElements());
        log.info("pagingResult.getContent() = {}", result.getContent());
    }

    // querydsl
//    @Test
//    public void search1Test(){
//
//        repository.search1();
//    }

}
