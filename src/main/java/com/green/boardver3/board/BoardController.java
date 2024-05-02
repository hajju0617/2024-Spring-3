package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.common.model.Paging;
import com.green.boardver3.common.model.ResultDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("board")
@Tag(name = "Board (게시판)", description = "게시판 CRUD")    // 각 기능마다 이름 지정 (name:제목 description:부제목 느낌)
@Slf4j
public class BoardController {
    private final BoardService service;

    @PostMapping
    @Operation(summary = "게시글 등록", description = "게시글 등록을 할 수 있습니다")    // post 네이밍 지정, 클릭하였을때 어떤 기능을 하는 지 설명
    public ResultDto<Long> postBoard(@RequestBody BoardPostReq p) {
        log.info("p의 파라미터: {}", p);     // {} -> System.out.printf(""); 느낌
        long result = service.postBoard(p);

        return ResultDto.<Long>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("")
                .resultData(result).build();
    }
    @GetMapping
    public BoardGetRes getBoard(@RequestParam long boardId) {
        return service.getBoard(boardId);
    }
    @DeleteMapping
    public ResultDto<Integer> deleteBoard(@RequestParam long boardId) {
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

    @GetMapping("{boardId}")   // 주소값에 /board_id값으로 조회 가능  localhost:8080/board/{board_id} ({board_id}에 값을 넣어서 조회)
                                   // {board_id} 값이 long "boardId" 로 들어감.
    public ResultDto<BoardDetailGetRes> getBoardOne(@PathVariable long boardId) {
        // (name="board_id") 쓴 이유 ---> 주소값에 boardId처럼 대문자는 안 들어가게 해줌 boardId 대신 board_id로 주소값에 표시 (변수값은 예외)
        // 원리?만 놓고 보면 PathVariable 과 RequestParam은 같다. 쓰고싶은 거 쓰면 됨
        // ("{boardId}") 쓴 이유 -->> 위에 getBoard @GetMapping과 똑같은 @GetMapping이라서 구분해주기 위해서 썼음.
        BoardDetailGetRes result = service.getBoardOne(boardId);

        return ResultDto.<BoardDetailGetRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(result == null ? "내용을 찾을 수 없습니다." : HttpStatus.OK.toString())
                .resultData(result).build();
    }
    @GetMapping("boardlist")
    public ResultDto<List<BoardGetRes>> getBoardList(@ModelAttribute Paging p) {
        List<BoardGetRes> list = service.getBoardList(p);
        // List 각 방 마다 BoardGetRes 타입을 가진 객체의 주소값이 담긴다.

        return ResultDto.<List<BoardGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(String.format("rowCount: %d", list.size()))
                .resultData(list).build();
    }
}

/*
PathVariable vs RequestParam
1. 둘 다 URL에 데이터를 담아서 보내는 케이스(Body에 데이터를 담는 방식(x))
    - 결과론적으로는 같음, 다만 방식의 차이
    - PathVariable ex) /board/1 -> 마지막 1을 데이터로 보내는 경우 (보통 PK값을 많이 씀)
    - PathVariable ex) /board/1/20 -> 1, 20을 보내는 경우
    - PathVariable은 해당 값이 정확히 어떤 값인지 알 수 없음 (기억 or 문서로 알아내야 함)

    = RequestParam은 쿼리스트링(파라미터)값을 받을 때 사용, key, value로 구성되어 있음
    = 쿼리스트링은 URL 뒤에 ?로 시작하고 &로 데이터를 구분
    = RequestParam ex) /board/?board_id=1&board_type=20
    = board_id, board_type : key / 1, 20 : value

ModelAttribute vs RequestParam
- 둘 다 쿼리스트링 데이터를 받을 수 있다.
- ModelAttribute 는 body 데이터(formData)도 받을 수 있다 (Not JSON 제이슨은 안됨)
- 해당하는 데이터가 안 넘어오면 거부할 수 있는 기능은 RequestParam뿐이다.
- (ex. RequestParam(name = "board_id") long boardId, RequestParam(name = "test") String test)
    -> 매개변수 2개 일 경우 두개의 값을 보내줘야 한다. 그러지 않으면 오류 발생
    -> RequestParam(name = "board_id") long boardId, RequestParam(name = "test", required = false) String test)
        -> 이렇게 해주면 값을 보내지 않아도 오류가 발생하지 않음
- 그에 반해 ModelAttribute는 일단 데이터를 받고 판단을 해야 한다. (로직 or validation)
 */

/*
Get, Delete Method
- @ModelAttribute, @PathVariable, @RequestParam 중에 하나 사용
Post, Put, Patch Method
- @RequestBody 사용
FileUpload
-Post Method and @RequestPart 사용
 */
