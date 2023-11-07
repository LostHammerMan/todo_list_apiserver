package org.zerock.apiserver.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 페이징 결과물 담는 dto - 목록 데이터
// 페이징 처리 로직
@Data
public class PagingResponseDto<E> { // E : element

    // dto 목록 데이터
    private List<E> dtoList;

    private List<Integer> pageNumList;

    private PagingRequestDto pagingRequestDto;

    private boolean prev, next;

    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(buildMethodName = "withAll") // 메서드 네임 지정
    public PagingResponseDto(List<E> dtoList, PagingRequestDto pagingRequestDto, long total){
        this.dtoList = dtoList;
        this.pagingRequestDto = pagingRequestDto;
        this.totalCount = (int)total;

        // 끝페이지부터 계산 end
            // 현재 페이지 번호 : pagingRequestDto.getPage()
        int end = (int)(Math.ceil(pagingRequestDto.getPage()/10.0)) * 10; // 12 페이지 -> 1.2 -> 2

        // 시작 값
        int start = end -9;

        // 찐 마지막 페이지
        int last = (int)(Math.ceil(totalCount / (double) pagingRequestDto.getSize()));

        end = end > last ? last : end;

        this.prev = start > 1;
        this.next = totalCount > end * pagingRequestDto.getSize();

        // 다시 확인
        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        // 이전 페이지 번호
        this.prevPage = prev ? start -1 : 0;

        // 다음 페이지 번호
        this.nextPage = next ? end +1 : 0;
    }

}
