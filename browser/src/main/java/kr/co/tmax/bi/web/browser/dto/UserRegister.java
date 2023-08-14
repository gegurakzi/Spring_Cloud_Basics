package kr.co.tmax.bi.web.browser.dto;

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
    @Nullable
    private String email;

}
