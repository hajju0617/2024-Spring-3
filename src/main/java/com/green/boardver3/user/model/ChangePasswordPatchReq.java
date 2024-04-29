package com.green.boardver3.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ChangePasswordPatchReq {
    @JsonIgnore private long userId;

    private String uid;
    private String currentPw;
    private String newPw;
}
