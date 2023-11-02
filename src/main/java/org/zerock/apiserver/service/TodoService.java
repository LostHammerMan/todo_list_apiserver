package org.zerock.apiserver.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.TodoDto;

@Transactional // 서비스 인터페이스, 서비스 클래스, 메서드 등에 사용 가능
               // 인터페이스에 하는 것 추천
public interface TodoService {

    // 조회
    TodoDto get(Long tno);

    // 등록 : auto_increment 되는 경우 pk 값을 리턴
    Long register(TodoDto todoDto);

    // 수정 :
    void modify(TodoDto dto);

    // 삭제
    void remove(Long tno);


    // default 사용시 기본 기능 추가 가능
    default TodoDto entityToDto(Todo todo){
        TodoDto todoDto = TodoDto.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .content(todo.getContent())
                .complete(todo.isComplete())
                .dueDate(todo.getDueDate())
                .build();

        return todoDto;
    }

    default Todo dtoToEntity(TodoDto todoDto){
        Todo todo = Todo.builder()
                .tno(todoDto.getTno())
                .title(todoDto.getTitle())
                .content(todoDto.getContent())
                .complete(todoDto.isComplete())
                .dueDate(todoDto.getDueDate())
                .build();

        return todo;
    }
}
