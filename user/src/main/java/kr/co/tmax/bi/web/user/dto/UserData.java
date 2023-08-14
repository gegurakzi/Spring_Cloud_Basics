package kr.co.tmax.bi.web.user.dto;

import kr.co.tmax.bi.web.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
public class UserData {

    private Integer userId;
    private String userName;
    private String email;
    private Timestamp creation;

    public UserData fromEntity(User user) {
        UserData data = new UserData();
        data.setUserId(user.getUserId());
        data.setUserName(user.getUserName());
        data.setEmail(user.getEmail());
        data.setCreation(user.getCreation());
        return data;
    }

}
