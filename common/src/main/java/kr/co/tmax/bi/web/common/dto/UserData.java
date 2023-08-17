package kr.co.tmax.bi.web.common.dto;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserData {

    private Integer userId;
    private String userName;
    private String email;
    private Timestamp creation;

}
