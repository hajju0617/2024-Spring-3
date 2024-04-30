package com.green.boardver3.comment.model;

import lombok.Data;

@Data
public class CommentPostReq {
    private String contents;
    private long writerId;
    private long boardId;
}
