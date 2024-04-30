package com.green.boardver3.comment.model;

import lombok.Data;

@Data
public class CommentGetRes {
    // 댓글 pk, 댓글 내용, 댓글 작성자 pk, 댓글 작성자 이름, 댓글 작성일시
    private long commentId;
    private String contents;
    private long writerId;
    private String writerNm;
    private String createdAt;
    // CommentPaging 으로 부터 요청받은 자료를 응답
}
