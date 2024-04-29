package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;

    public int postBoard(BoardPostReq p) {
        return mapper.postBoard(p);
    }
    public BoardGetRes getBoard(long boardId) {
        return mapper.getBoard(boardId);
    }
    public int deleteBoard(long boardId) {
        return mapper.deleteBoard(boardId);
    }
    public int putBoard(BoardPutReq p) {
        return mapper.putBoard(p);
    }
    public BoardDetailGetRes getBoardOne(long boardId) {
        return mapper.getBoardOne(boardId);
    }
    public List<BoardGetRes> getBoardList(BoardGetReq p) {
        System.out.println("---ssssss----------" + p);
        return mapper.getBoardList(p);
    }
}
