package kr.co.tmax.bi.web.user.repository;

import io.micrometer.observation.annotation.Observed;
import kr.co.tmax.bi.web.user.controller.UserController;
import kr.co.tmax.bi.web.user.dto.UserData;
import kr.co.tmax.bi.web.user.dto.UserRegister;
import kr.co.tmax.bi.web.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserRepository implements UserController {
    private static Logger LOG = Logger.getLogger(UserRepository.class.getName());
    private static RowMapper<User> userMapper = (rs, rowNum) -> User.builder()
            .userId(rs.getInt("user_id"))
            .userName(rs.getString("user_name"))
            .password(rs.getString("password"))
            .creation(rs.getTimestamp("creation"))
            .build();

    private final RestTemplate rest;
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    @Observed(name = "user.add-user")
    public ResponseEntity<UserData> findByUserId(Integer userId) {
        final MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userId", userId);
        LOG.info("getUser() Called: " + userId);
        User user = jdbc.queryForObject(
                "SELECT * FROM users WHERE user_id=:userId LIMIT 1;",
                namedParameters,
                userMapper);
        LOG.info("User Queried: " + user);
        UserData userData = new UserData().fromEntity(user);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @Override
    @Observed(name = "user.get-user")
    public ResponseEntity<Void> insert(UserRegister user) throws DataAccessException {
        final MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userName", user.getUserName())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail());
        jdbc.update(
                "INSERT INTO users(user_name, password, email) VALUES (:userName, :password, :email);",
                namedParameters);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateByUserId(UserData user) {
        final MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userName", user.getUserName())
                .addValue("email", user.getEmail());
        jdbc.update(
                "INSERT INTO users(user_name, password, email) VALUES (:userName, :password, :email);",
                namedParameters);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}