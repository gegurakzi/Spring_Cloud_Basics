package kr.co.tmax.bi.web.user.dto;

import kr.co.tmax.bi.web.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@NoArgsConstructor
@Getter
@Setter
public class UserRegister {

    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String email;

    public UserRegister fromEntity(User user) {
        UserRegister data = new UserRegister();
        data.setUserName(user.getUserName());
        data.setPassword(user.getPassword());
        data.setEmail(user.getEmail());
        return data;
    }

}
