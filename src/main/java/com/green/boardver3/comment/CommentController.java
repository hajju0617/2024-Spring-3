package com.green.boardver3.comment;

import com.green.boardver3.comment.model.*;
import com.green.boardver3.common.model.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    @PostMapping
    public ResultDto<Integer> postComment(@RequestBody CommentPostReq p) {
       int result = service.postComment(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result).build();
    }
    @DeleteMapping
    public ResultDto<Integer> deleteComment(@ModelAttribute CommentDeleteReq p) {
        int result = service.deleteComment(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result).build();
    }
    @PutMapping
    public ResultDto<Integer> putComment(@RequestBody CommentPutReq p) {
        int result = service.putComment(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result).build();
    }
    @GetMapping("getlist")
    public ResultDto<List<CommentGetRes>> getComments(@ModelAttribute CommentPaging p) {
        List<CommentGetRes> list = service.getComments(p);

        return ResultDto.<List<CommentGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(list).build();
    }
}
