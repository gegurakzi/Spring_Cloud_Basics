package kr.co.tmax.bi.web.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDto {

    public Integer userId;
    public String userName;
    public String password;
    public String email;
    public String creation;

}
