package com.green.boardver3.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BoardPostReq {
    @JsonIgnore     // swagger 에서 example value 값 입력칸에 안 뜨게 해줌
    private long boardId;   // PK 값
    @Schema(example = "제목 테스트", description = "글 제목", requiredMode = Schema.RequiredMode.REQUIRED)
    // swagger 에서 요청할 값 미리보기 예시 설정

    private String title;
    @Schema(example = "내용 테스트", description = "글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contents;

    @JsonProperty("writer_id")  //swagger example value 에서 "writerId"를 "writer_id"로 변경해줌
    // ConstructorProperties는 자바 스프링 프레임워크에서 사용되고
    // Bind Param은 스프링 MVC에서 사용됩니다
    // @JsonProperty는 Jackson 라이브러리에서 사용됩니다.

    // swagger 에서 요청할 값 미리보기 예시 설정
    @Schema(example = "1", description = "유저PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private long writerId;
}
