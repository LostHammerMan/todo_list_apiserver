package org.zerock.apiserver.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.QTodo;
import org.zerock.apiserver.domain.Todo;
import org.zerock.apiserver.dto.PagingRequestDto;

import java.util.List;

@Slf4j
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl(){
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1(PagingRequestDto pagingRequestDto) {

        log.info("search1.....................");

        // 쿼리를 날리기 위한 객체
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);
        query.where(todo.title.contains("1"));

        // 페이징 처리
        Pageable pageable = PageRequest.of(
                pagingRequestDto.getPage()-1,
                pagingRequestDto.getSize(),
                Sort.by("tno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        List<Todo> list = query.fetch(); // fetch : 목록 데이터
        long total = query.fetchCount(); // Long 타입

        return new PageImpl<>(list, pageable, total);
    }
}
