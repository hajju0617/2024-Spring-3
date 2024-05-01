package com.green.boardver3.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class BoardPostReq {
    @JsonIgnore private long boardId;   // PK 값

    private String title;
    private String contents;
    private long writerId;
}
