package kr.ac.jejunu;

import javax.sql.DataSource;
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

    private JdbcContext jdbcContext;

    public User get(Long id) throws ClassNotFoundException, SQLException {
        String SQL = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};

        return jdbcContext.queryForObject(SQL, params);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into userinfo(name, password) VALUES (?,?)";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        return jdbcContext.insert(sql, params);
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE userinfo SET name = ?, password = ? WHERE id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM userinfo WHERE id = ?";
        Object[] params = new Object[]{id};
        jdbcContext.update(sql, params);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }
}
