package com.green.boardver3.comment.model;

import com.green.boardver3.common.model.Paging;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.BindParam;

import java.beans.ConstructorProperties;

@Getter
@ToString(callSuper = true)
public class CommentPaging extends Paging {
    @Schema(name = "board_id", example = "1", description = "게시글PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private final long boardId;

    @ConstructorProperties({"page", "size", "board_id"})        // 쿼리스트링
    // 프론트로부터 받을 자료 (Request)
    public CommentPaging(Integer page, Integer size, @BindParam("board_id") long boardId) {
        super(page, size);
        this.boardId = boardId;
    }
}
