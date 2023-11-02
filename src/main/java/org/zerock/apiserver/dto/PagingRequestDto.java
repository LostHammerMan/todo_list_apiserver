package org.zerock.apiserver.dto;


import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder // 상속을 해야 하는 경우가 필요할 때
public class PagingRequestDto {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    public int size = 10;
}
