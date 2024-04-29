package com.green.boardver3.user;

import com.green.boardver3.user.model.ChangePasswordPatchReq;
import com.green.boardver3.user.model.SinginPostReq;
import com.green.boardver3.user.model.UserEntity;
import com.green.boardver3.user.model.UserPostReq;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public int postUser(UserPostReq p) {
        String hashPassword = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
        p.setUpw(hashPassword);
        return mapper.postUser(p);
    }
    public int postSignIn(SinginPostReq p) {
        UserEntity entity = mapper.getUserById(p.getUid());
        // 1 -> 로그인 성공, 2 -> 아이디 없음, 3 -> 비밀번호 다름
        if(entity == null) {
            return 2;
        }
        if(BCrypt.checkpw(p.getUpw(), entity.getUpw())) {
            return 1;
        }
        return 3;
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
        p.setUserId(entity.getUserId());
        System.out.println("mapper 에 들어갈 값: " + p);
        return mapper.patchPassword(p);
    }

}
