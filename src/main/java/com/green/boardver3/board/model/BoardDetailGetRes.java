package com.green.boardver3.board.model;

import com.green.boardver3.comment.model.CommentGetRes;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardDetailGetRes {
    private long boardId;
    private String title;
    private String contents;
    private long writerId;
    private String writerNm;
    private int hits;
    private String createdAt;
    private List<CommentGetRes> comments;
}
