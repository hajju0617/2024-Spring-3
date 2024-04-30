package com.green.boardver3.user;

import com.green.boardver3.common.model.ResultDto;
import com.green.boardver3.user.model.ChangePasswordPatchReq;
import com.green.boardver3.user.model.SinginPostReq;
import com.green.boardver3.user.model.UserPostReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService service;


    @PostMapping("signup")
    public ResponseEntity<ResultDto<Integer>> UserPostReq(@RequestBody UserPostReq p) {
        int result = service.postUser(p);

        return ResponseEntity.ok(ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result).build());
    }
    @PostMapping("signin")
    public ResponseEntity<ResultDto<Integer>> postSignIn(@RequestBody SinginPostReq p) {
        int result = service.postSignIn(p);
        // 1 -> 로그인 성공, 2 -> 아이디를 확인해 주세요., 3 -> 비밀번호를 확인해 주세요.
        String str = switch (result) {
            case 1 -> "로그인 성공";
            case 2 -> "아이디를 확인해 주세요";
            case 3 -> "비밀번호를 확인해 주세요";
            default -> "Error";
        };
        return ResponseEntity.ok(ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("")
                .resultData(result).build());
    }
    @PatchMapping("password")
    public ResultDto<Integer> patchPassword(@RequestBody ChangePasswordPatchReq p) {
        int result = service.patchPassword(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("")
                .resultData(result).build();
    }
}
