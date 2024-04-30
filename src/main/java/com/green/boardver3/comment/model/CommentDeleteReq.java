package com.green.boardver3.comment.model;

import lombok.Data;

@Data
public class CommentDeleteReq {
    private long commentId;
    private long writerId;
}
