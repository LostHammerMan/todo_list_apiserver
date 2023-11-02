package org.zerock.apiserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.TodoDto;
import org.zerock.apiserver.repository.TodoRepository;

import java.util.Optional;

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

    @Override
    public TodoDto entityToDto(Todo todo) {
        return TodoService.super.entityToDto(todo);
    }

    @Override
    public Todo dtoToEntity(TodoDto todoDto) {
        return TodoService.super.dtoToEntity(todoDto);
    }
}
