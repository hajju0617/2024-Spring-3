package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.comment.CommentMapper;
import com.green.boardver3.comment.model.CommentGetRes;
import com.green.boardver3.comment.model.CommentPaging;
import com.green.boardver3.common.GlobalConst;
import com.green.boardver3.common.model.Paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j  // log 사용
public class BoardService {
    private final BoardMapper mapper;
    private final CommentMapper commentMapper;

    public long postBoard(BoardPostReq p) {
        log.info("(1) p.boardId: {}", p.getBoardId());
        int result = mapper.postBoard(p);   // result 에는 영향받은 행 값
        log.info("(2) p.boardId: {}", p.getBoardId());
        if(result == 0) {
            return 0L;
        }
        return p.getBoardId();  // pk값 반환
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
//        return mapper.getBoardOne(boardId);
        BoardDetailGetRes result = mapper.getBoardOne(boardId);
        if(result != null) {
            // Record 가 있다면 조회수 +1
            mapper.patchBoardHits(boardId);
        }
        CommentPaging paging = new CommentPaging(1, GlobalConst.COMMENT_PAGING_SIZE, boardId);
        // 어느 글(boardId)이고 댓글 1번째 페이지 5개
        List<CommentGetRes> comments = commentMapper.getComments(paging);
        result.setComments(comments);

        if(comments.size() < GlobalConst.COMMENT_PAGING_SIZE) {
            result.setTotalCommentPage(1);
            // 댓글 5개 이하면 페이지는 단 1개
        } else {
            int totalCommentPage = commentMapper.getTotalCommentPage(paging);
            result.setTotalCommentPage(totalCommentPage);
        }
        return result;
    }
    public List<BoardGetRes> getBoardList(Paging p) {
        return mapper.getBoardList(p);
    }
}
