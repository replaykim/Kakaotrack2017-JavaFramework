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
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement;

        };
        return jdbcContext.jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo(name, password) VALUES (?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            return preparedStatement;
        };
        return jdbcContext.jdbcContextWithStatementStrategyForInsert(statementStrategy);
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        //(Connection connection 으로 명확히 해줄수 있고, 안해줘도 실행하는 부분에서 알아서 확인함.)
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE userinfo SET name = ?, password = ? WHERE id = ?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            return preparedStatement;
        };
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM userinfo WHERE id = ?");
            preparedStatement.setLong(1,id);
            return preparedStatement;
        };

        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }


//    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
//        Class.forName("com.mysql.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://117.17.102.106:3306/replayDB?characterEncoding=utf-8", "root", "1234");

}
