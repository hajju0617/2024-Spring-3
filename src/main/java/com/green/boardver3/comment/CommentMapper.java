package com.green.boardver3.comment;

import com.green.boardver3.comment.model.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.*;

@Mapper
public interface CommentMapper {
    int postComment(CommentPostReq p);
    int deleteComment(CommentDeleteReq p);
    int putComment(CommentPutReq p);
    List<CommentGetRes> getComments(CommentPaging p);
}
