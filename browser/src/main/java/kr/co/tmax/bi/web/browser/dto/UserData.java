package kr.co.tmax.bi.web.browser.dto;

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

}
