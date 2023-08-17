package kr.co.tmax.bi.web.user.repository;

import io.micrometer.observation.annotation.Observed;
import kr.co.tmax.bi.web.user.controller.UserController;
import kr.co.tmax.bi.web.common.dto.UserData;
import kr.co.tmax.bi.web.common.dto.UserRegister;
import kr.co.tmax.bi.web.user.entity.User;
import kr.co.tmax.bi.web.user.util.DtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UserRepository implements UserController {
    private static Logger LOG = Logger.getLogger(UserRepository.class.getName());
    private static RowMapper<User> userMapper = (rs, rowNum) -> User.builder()
            .userId(rs.getInt("user_id"))
            .userName(rs.getString("user_name"))
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .creation(rs.getTimestamp("creation"))
            .build();

    private final RestTemplate rest;
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    @Observed(name = "user.find-by-userid")
    public ResponseEntity<UserData> findByUserId(Integer userId) throws DataAccessException {
        final MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userId", userId);
        LOG.info("findByUserId() Called: " + userId);
        User user;
        try {
            user = jdbc.queryForObject(
                    "SELECT * FROM users WHERE user_id=:userId LIMIT 1;",
                    namedParameters,
                    userMapper);
            LOG.info("User Queried: " + user);
        } catch (EmptyResultDataAccessException e) {
            LOG.info("Empty Result: 'SELECT * FROM users WHERE user_id=:userId LIMIT 1;', :userId=" + userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        assert user != null;
        UserData userData = DtoUtil.USERDATA.fromEntity(user);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @Override
    @Observed(name = "user.find-by-email")
    public ResponseEntity<UserData> findByEmail(String email) throws DataAccessException {
        final MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("email", email);
        LOG.info("findByEmail() Called: " + email);
        User user;
        try {
            user = jdbc.queryForObject(
                    "SELECT * FROM users WHERE email=:email LIMIT 1;",
                    namedParameters,
                    userMapper);
            LOG.info("User Queried: " + user);
        } catch (EmptyResultDataAccessException e) {
            LOG.info("Empty Result: 'SELECT * FROM users WHERE email=:email LIMIT 1;', :email=" + email);
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        assert user != null;
        UserData userData = DtoUtil.USERDATA.fromEntity(user);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @Override
    @Observed(name = "user.insert")
    public ResponseEntity<Void> insert(UserRegister user) throws DataAccessException {
        final MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("userName", user.getUserName())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail());
        LOG.info("insert() Called: " + namedParameters);
        jdbc.update(
                "INSERT INTO users(user_name, password, email) VALUES (:userName, :password, :email);",
                namedParameters);
        LOG.info("User Inserted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Observed(name = "user.update-by-userid")
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