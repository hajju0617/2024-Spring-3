package com.green.boardver3.comment.model;

import com.green.boardver3.common.model.Paging;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.BindParam;

@Getter
public class CommentPaging extends Paging {
    private long boardId;

    // 프론트로부터 받을 자료 (Request)
    public CommentPaging(Integer page, Integer size, long boardId) {
        super(page, size);
        this.boardId = boardId;
    }
}
