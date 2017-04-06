package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by blue on 2017-04-06.
 */
public class UpdateUserStatement implements StatementStrategy {
    @Override
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException {
        User user = (User) object;

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE userinfo SET name = ?, password = ? WHERE id = ?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setLong(3, user.getId());
        return preparedStatement;
    }
}
