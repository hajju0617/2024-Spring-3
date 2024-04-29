package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.*;

@Mapper
public interface BoardMapper {
    int postBoard(BoardPostReq p);
    BoardGetRes getBoard(long boardId);

    int deleteBoard(long boardId);
    int putBoard(BoardPutReq p);

    BoardDetailGetRes getBoardOne(long boardId);

    List<BoardGetRes> getBoardList(BoardGetReq p);
}
