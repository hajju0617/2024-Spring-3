package com.green.boardver3.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.beans.ConstructorProperties;


@Data
public class Paging {
    private int page; //페이지 값
    private int size; //페이지 당 레코드 수

    @ConstructorProperties({"page", "size"})
    public Paging(Integer page, Integer size) { //파라미터가 있는 생성자
        this.page = page == null ? 1 : page;
        this.size = size == null ? 10 : size;
        this.startIdx = (this.page - 1) * this.size;
        this.len = this.size;
    }

    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int len;
}
