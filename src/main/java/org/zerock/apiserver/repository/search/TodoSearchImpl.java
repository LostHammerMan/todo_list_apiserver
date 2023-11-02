package org.zerock.apiserver.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.apiserver.domain.QTodo;
import org.zerock.apiserver.domain.Todo;

import java.util.List;

@Slf4j
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl(){
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1() {

        log.info("search1.....................");

        // 쿼리를 날리기 위한 객체
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);
        query.where(todo.title.contains("1"));

        // 페이징 처리
        Pageable pageable = PageRequest.of(1, 10, Sort.by("tno").descending());
        this.getQuerydsl().applyPagination(pageable, query);

        List<Todo> fetch = query.fetch(); // fetch : 목록 데이터
        query.fetchCount(); // Long 타입
        log.info("fetch = {}", fetch);

        return null;
    }
}
