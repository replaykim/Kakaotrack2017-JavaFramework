package kr.ac.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

/**
 * Created by replay on 2017. 3. 15..
 *
 * try/catch 와 throw => 자신감의 차이!
 *
 * jdbc는 컴파일할땐 필요없는 런타임 클래스
 * Connection, preparedStatement, resultSrt 는 스트림 클래스이기때문에 자원낭비를 방지하게 위해 닫아준다.
 *
 *
 * extract method
 */

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from userdata where id = ?";
        Object[] params = new Object[]{id};
        User resultUser = null;

        try {
            resultUser = jdbcTemplate.queryForObject(sql, params,(resultSet, i) -> {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));

                return user;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return resultUser;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO userdata VALUES (?,?,?)";
        Object[] params = new Object[]{user.getId(), user.getName(), user.getPassword()};

        jdbcTemplate.update(sql, params);
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM userdata where id = ?";
        Object[] params = new Object[]{id};

        jdbcTemplate.update(sql, params);
    }




}
