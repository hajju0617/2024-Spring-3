package com.green.boardver3.comment.model;

import lombok.Data;

@Data
public class CommentPutReq {
    private long commentId;
    private String contents;
    private long writerId;
}
