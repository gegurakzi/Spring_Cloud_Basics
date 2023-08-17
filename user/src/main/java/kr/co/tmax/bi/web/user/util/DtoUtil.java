package kr.co.tmax.bi.web.user.util;

import kr.co.tmax.bi.web.common.dto.UserData;
import kr.co.tmax.bi.web.common.dto.UserRegister;
import kr.co.tmax.bi.web.user.entity.User;

public class DtoUtil {

    public static class USERDATA {

        public static UserData fromEntity(User user) {
            UserData data = new UserData();
            data.setUserId(user.getUserId());
            data.setUserName(user.getUserName());
            data.setEmail(user.getEmail());
            data.setCreation(user.getCreation());
            return data;
        }
    }
    public static class USERREGISTER {
        public UserRegister fromEntity(User user) {
            UserRegister data = new UserRegister();
            data.setUserName(user.getUserName());
            data.setPassword(user.getPassword());
            data.setEmail(user.getEmail());
            return data;
        }
    }


}
