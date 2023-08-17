package kr.co.tmax.bi.web.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;

@Getter
@Builder
public class User {

    @NonNull
    private Integer userId;
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String email;
    @NonNull
    private Timestamp creation;

}
