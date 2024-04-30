package com.green.boardver3.comment;

import com.green.boardver3.comment.model.CommentDeleteReq;
import com.green.boardver3.comment.model.CommentPostReq;
import com.green.boardver3.comment.model.CommentPutReq;
import com.green.boardver3.comment.model.CommentUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper mapper;

    public int postComment(CommentPostReq p) {
        return mapper.postComment(p);
    }
    public int deleteComment(CommentDeleteReq p) {
        // 로그인한 사용자의 pk값을 p에 대입
        return mapper.deleteComment(p)  ;
    }
    public int putComment(CommentPutReq p) {
        return mapper.putComment(p);
    }
}
