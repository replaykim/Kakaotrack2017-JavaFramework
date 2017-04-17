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

        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public Long add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        return jdbcContext.jdbcContextWithStatementStrategyForAdd(statementStrategy);
    }

    public void delete(Long id) {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }
}
