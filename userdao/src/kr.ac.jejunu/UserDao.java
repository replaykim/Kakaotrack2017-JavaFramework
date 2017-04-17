package kr.ac.jejunu;

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

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    JdbcContext jdbcContext;


    public User get(Long id) throws ClassNotFoundException, SQLException {

        StatementStrategy statementStrategy = connection -> {
            String sql = "select * from userinfo where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            return preparedStatement;
        };
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public Long add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = connection -> {
            String sql = "insert into userinfo(name, password) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            return preparedStatement;
        };
        return jdbcContext.jdbcContextWithStatementStrategyForAdd(statementStrategy);
    }

    public void delete(Long id) {
        StatementStrategy statementStrategy = connection -> {
            String sql = "DELETE FROM userinfo WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            return preparedStatement;
        };
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }
}
