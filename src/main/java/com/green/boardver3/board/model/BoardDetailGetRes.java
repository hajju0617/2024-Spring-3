package com.green.boardver3.board.model;

import lombok.Data;

@Data
public class BoardDetailGetRes {
    private long boardId;
    private String title;
    private String contents;
    private long writerId;
    private String writerNm;
    private int hits;
    private String createdAt;
}
