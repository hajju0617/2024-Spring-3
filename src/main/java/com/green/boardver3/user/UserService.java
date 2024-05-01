package com.green.boardver3.user;

import com.green.boardver3.common.model.ResultDto;
import com.green.boardver3.user.model.ChangePasswordPatchReq;
import com.green.boardver3.user.model.SignInPostReq;
import com.green.boardver3.user.model.UserEntity;
import com.green.boardver3.user.model.UserPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper mapper;

    public int postUser(UserPostReq p) {
        String hashPassword = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
        p.setUpw(hashPassword);
        return mapper.postUser(p);
    }
    public ResultDto<Long> postSignIn(SignInPostReq p) {
        UserEntity entity = mapper.getUserById(p.getUid());
        if(entity == null) {    // 아이디 없음
            return ResultDto.<Long>builder()
                .statusCode(HttpStatus.NOT_FOUND)       // 404
                .resultMsg("아이디를 확인해 주세요")
                .resultData(0L)
                .build();
        } else if(!BCrypt.checkpw(p.getUpw(), entity.getUpw())) {
            return ResultDto.<Long>builder()
                    .statusCode(HttpStatus.NOT_FOUND)
                    .resultMsg("비밀번호를 확인해 주세요")
                    .resultData(0L)
                    .build();
        }
        return ResultDto.<Long>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("로그인 성공")
                .resultData(0L)
                .build();
    }

    public int patchPassword(ChangePasswordPatchReq p) {
        UserEntity entity = mapper.getUserById(p.getUid());
        System.out.println("유저가 입력한 값: " + p);
        // 1: 비밀번호 변경 성공, 2: 아이디를 확인해 주세요. 3:기존 비밀번호를 확인 해주세요
        if(entity == null) {
            return 2;
        }
        if(!BCrypt.checkpw(p.getCurrentPw(), entity.getUpw())) {
            return 3;
        }
        String hashedPassword = BCrypt.hashpw(p.getNewPw(), BCrypt.gensalt());
        p.setNewPw(hashedPassword);
        System.out.println("set userid 적은 이유: "+  entity);
        p.setUserId(entity.getUserId());        // WHERE user_id = #{userId} 값으로 쓰려고.
        System.out.println("mapper 에 들어갈 값: " + p);
        return mapper.patchPassword(p);
    }
}
