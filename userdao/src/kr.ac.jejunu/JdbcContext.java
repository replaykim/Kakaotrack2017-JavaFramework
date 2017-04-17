package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by replay on 2017. 4. 17..
 */
public class JdbcContext {
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    DataSource dataSource;


    public User jdbcContextWithStatementStrategyForGet(StatementStrategy statementStrategy) {

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            resultSet = preparedStatement.executeQuery();

            if ( resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return user;
    }

    public Long jdbcContextWithStatementStrategyForAdd(StatementStrategy statementStrategy){
        Long id = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("select last_insert_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            id = resultSet.getLong(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return id;
    }
    public void jdbcContextWithStatementStrategyForUpdate(StatementStrategy statementStrategy){
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement= statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public User queryForObject(String sql, Object[] param) {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1 ; i <= param.length; i++) {
                preparedStatement.setObject(i, param[i-1]);
            }
            return preparedStatement;
        };
        return jdbcContextWithStatementStrategyForGet(statementStrategy);
    }

    public Long insert(String sql, Object[] param) {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1 ; i<=param.length;i++){
                preparedStatement.setObject(i, param[i-1]);
            }
            return preparedStatement;
        };
        return jdbcContextWithStatementStrategyForAdd(statementStrategy);
    }

    public void update(String sql, Object[] param) {

        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1 ; i<=param.length;i++){
                preparedStatement.setObject(i, param[i-1]);
            }
            return preparedStatement;
        };
        jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }
}
