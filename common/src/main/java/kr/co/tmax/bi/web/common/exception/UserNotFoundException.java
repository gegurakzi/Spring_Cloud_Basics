package kr.co.tmax.bi.web.common.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer userId) {
        super(String.valueOf(userId));
    }
}
