package kr.ac.jejunu;

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

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
        return jdbcContext.JdbcContextWithStatementStrategtForquery(statementStrategy);
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        StatementStrategy statementStrategy = new AddUserStatementStrategy(user);
        jdbcContext.JdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategy(id);
        jdbcContext.JdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }


}
