package com.green.boardver3.comment.model;

import lombok.Data;

@Data
public class CommentUpdateReq {
    private long commentId;
    private String contents;
    private long writerId;
}
