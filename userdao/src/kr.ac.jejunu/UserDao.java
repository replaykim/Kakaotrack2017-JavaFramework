package kr.ac.jejunu;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by replay on 2017. 3. 15..
 * <p>
 * try/catch 와 throw => 자신감의 차이!
 * <p>
 * jdbc는 컴파일할땐 필요없는 런타임 클래스
 * Connection, preparedStatement, resultSrt 는 스트림 클래스이기때문에 자원낭비를 방지하게 위해 닫아준다.
 * <p>
 * <p>
 * extract method
 */

public class UserDao {
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    JdbcTemplate jdbcTemplate;

    public User get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] param = new Object[]{id};

        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, param, (resultSet, i) -> {
                User user1 = new User();
                user1.setId(resultSet.getLong("id"));
                user1.setPassword(resultSet.getString("password"));
                user1.setName(resultSet.getString("name"));

                return user1;
            });
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
        }

        return user;
    }

    public Long add(User user) throws SQLException, ClassNotFoundException {
        String sql = "insert into userinfo(name, password) VALUES (?,?)";
        Object[] param = new Object[]{user.getName(), user.getPassword()};
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            return preparedStatement;
        }, keyHolder);
        return (Long)keyHolder.getKey();

    }

    public void delete(Long id) {
        String sql = "DELETE FROM userinfo WHERE id=?";
        Object[] param = new Object[]{id};
        jdbcTemplate.update(sql, param);
    }
}
