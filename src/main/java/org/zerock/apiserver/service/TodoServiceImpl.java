package org.zerock.apiserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PagingRequestDto;
import org.zerock.apiserver.dto.PagingResponseDto;
import org.zerock.apiserver.dto.TodoDto;
import org.zerock.apiserver.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDto get(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);

        Todo findTodo = result.orElseThrow();

        return entityToDto(findTodo);
    }

    @Override
    public Long register(TodoDto todoDto) {
        Todo todo = dtoToEntity(todoDto);
        Todo result = todoRepository.save(todo);

        return result.getTno();
    }

    @Override
    public void modify(TodoDto dto) {
        Optional<Todo> result = todoRepository.findById(dto.getTno());
        Todo findTodo = result.orElseThrow();
        findTodo.setTitle(dto.getTitle());
        findTodo.setContent(dto.getContent());
        findTodo.setComplete(dto.isComplete());
        findTodo.setDueDate(dto.getDueDate());

        todoRepository.save(findTodo);
    }

    @Override
    public void remove(Long tno) {
        todoRepository.deleteById(tno);
    }

    // 페이징
    @Override
    public PagingResponseDto<TodoDto> getList(PagingRequestDto pagingRequestDto) {

        // JPA
        Page<Todo> result = todoRepository.search1(pagingRequestDto);

        // todoListDto 로 변환 -> 화면에 출력되야 하는 것은 dto
        // map() : 데이터 변환
        // collect() : 원하는 컬렉션으로 변환
        List<TodoDto> dtoList =  result
                .get()
                .map(todo -> entityToDto(todo)).collect(Collectors.toList());

        PagingResponseDto<TodoDto> responseDto =
                PagingResponseDto.<TodoDto>builder()
                        .dtoList(dtoList)
                        .pagingRequestDto(pagingRequestDto)
                        .total(result.getTotalElements())
                        .withAll();


        return responseDto;
    }

    @Override
    public TodoDto entityToDto(Todo todo) {
        return TodoService.super.entityToDto(todo);
    }

    @Override
    public Todo dtoToEntity(TodoDto todoDto) {
        return TodoService.super.dtoToEntity(todoDto);
    }
}
