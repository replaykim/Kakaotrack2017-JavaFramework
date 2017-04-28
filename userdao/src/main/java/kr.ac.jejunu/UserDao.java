package kr.ac.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.sql.Statement;

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

    public User get(Long id) throws ClassNotFoundException, SQLException {
        String SQL = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};

        User user1 = null;
        try {
            user1 = jdbcTemplate.queryForObject(SQL, params, (resultSet, i) -> {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));

                return user;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user1;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo(name, password) VALUES (?,?)";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            //Statement.RETURN_GENERATED_KEYS > 자동으로 생성된 키값도 가져와라
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            return preparedStatement;
        }, keyHolder);
        return (Long) keyHolder.getKey();
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE userinfo SET name = ?, password = ? WHERE id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM userinfo WHERE id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
