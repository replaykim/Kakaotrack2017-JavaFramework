package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by replay on 2017. 4. 7..
 */
public class JdbcContext {
    public void setDatasouce(DataSource datasouce) {
        this.datasouce = datasouce;
    }

    private DataSource datasouce;

    public User jdbcContextWithStatementStrategyForGet(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = datasouce.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return user;
    }

    public Long jdbcContextWithStatementStrategyForInsert(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;

        try {
            connection = datasouce.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("select last_insert_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            id = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){

                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){

                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return id;
    }

    public void jdbcContextWithStatementStrategyForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = datasouce.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
    public User queryForObject(String SQL, Object[] params) throws SQLException {
        //(Connection connection 으로 명확히 해줄수 있고, 안해줘도 실행하는 부분에서 알아서 확인함.)

        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            for (int i=1; i<=params.length; i++){
                preparedStatement.setObject(i,params[i-1]);
            }
            return preparedStatement;

        };
        return jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public Long insert(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1; i <= params.length; i++){
                preparedStatement.setObject(i, params[i-1]);
            }
            return preparedStatement;
        };
        return jdbcContextWithStatementStrategyForInsert(statementStrategy);
    }

    public void update(String sql, Object[] params) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i =1 ; i <= params.length; i++){
                preparedStatement.setObject(i, params[i-1]);
            }
            return preparedStatement;
        };

        jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }
}
