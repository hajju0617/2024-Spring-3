package com.green.boardver3.user;

import com.green.boardver3.user.model.ChangePasswordPatchReq;
import com.green.boardver3.user.model.UserEntity;
import com.green.boardver3.user.model.UserPostReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
        int postUser(UserPostReq p);
        UserEntity getUserById(String uid);
        int patchPassword(ChangePasswordPatchReq p);

}
