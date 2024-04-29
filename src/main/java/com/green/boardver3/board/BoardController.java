package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.common.ResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("board")
public class BoardController {
    private final BoardService service;

    @PostMapping
    public ResultDto<Integer> postBoard(@RequestBody BoardPostReq p) {
        int result = service.postBoard(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("")
                .resultData(result).build();
    }
    @GetMapping
    public BoardGetRes getBoard(@RequestParam long boardId) {
        return service.getBoard(boardId);
    }
    @DeleteMapping
    public ResultDto<Integer> deleteBoard(@RequestParam(name="board_id") long boardId) {
        int result = service.deleteBoard(boardId);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result).build();
    }
    @PutMapping
    public ResultDto<Integer> putBoard(@RequestBody BoardPutReq p) {
        int result = service.putBoard(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result).build();
    }
    @GetMapping("{board_id}")   // 주소값에 /board_id값으로 조회 가능
    public ResultDto<BoardDetailGetRes> getBoardOne(@PathVariable(name="board_id") long boardId) {
        // (name="board_id") 쓴 이유 ---> 주소값에 boardId처럼 대문자는 안 들어가게 해줌 boardId 대신 board_id로 주소값에 표시 (변수값은 예외)
        // 원리?만 놓고 보면 PathVariable 과 RequestParam은 같다 쓰고싶은 거 쓰면 됨
        // 다만 위에 getBoard @GetMapping과 똑같은 @GetMapping이라서 구분해주기 위해서 썼음.
        BoardDetailGetRes result = service.getBoardOne(boardId);

        return ResultDto.<BoardDetailGetRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(result == null ? "내용을 찾을 수 없습니다." : HttpStatus.OK.toString())
                .resultData(result).build();
    }
    @GetMapping("boardlist")
    public ResultDto<List<BoardGetRes>> getBoardList(@ModelAttribute BoardGetReq p) {
        System.out.println("-------------" + p);
        List<BoardGetRes> list = service.getBoardList(p);
        // List 각 방 마다 BoardGetRes 타입을 가진 객체의 주소값이 담긴다.


        return ResultDto.<List<BoardGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(String.format("rowCount: %d", list.size()))
                .resultData(list).build();
    }
}
