package org.zerock.apiserver.domain;

import jakarta.persistence.*;
import lombok.*;

import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Builder // 빌더 사용시 기본생성자, 생성자 필요
@AllArgsConstructor @NoArgsConstructor
@Table(name = "TBL_TODO")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(length = 500, nullable = false)
    private String title;
    private String content;
    private boolean complete;
    private LocalDate dueDate;

    public void setTno(Long tno) {
        this.tno = tno;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // 변경 메서드
    public void updateTodo(String title, String content, boolean complete, LocalDate dueDate){
        this.title = title;
        this.content = content;
        this.complete = complete;
        this.dueDate = dueDate;
    }
}
