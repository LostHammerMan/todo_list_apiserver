package org.zerock.apiserver.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder // 부모 객체를 상속 받는 자식 객체를 만들 떄, 부모 객체의 필드값도 지정할 수 있게 하기 위해 사용
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingRequestDto {

    @Builder.Default // 빌더 패턴을 통해 인스턴스를 만들 떄 특정 필드를 특정 값으로 초기화
    private int page = 1;

    @Builder.Default
    public int size = 10;
}
