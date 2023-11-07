package org.zerock.apiserver.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 -> JSON 변환시 필요
public class TodoDto {

    // dto 설계 팁
    /*
    * 엔티티와 유사하게 설계
    * but, 데이터 변경이 많으므로 @Data 사용해도 무방
    * dto 는 하나의 엔티티에 여러개 존재 가능
    * */

    private Long tno;

    private String title;

    private String content;

    private boolean complete;

    private LocalDate dueDate;

}
