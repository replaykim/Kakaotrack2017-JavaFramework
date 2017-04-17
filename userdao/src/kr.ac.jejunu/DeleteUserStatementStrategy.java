package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by replay on 2017. 4. 14..
 */
public class DeleteUserStatementStrategy implements StatementStrategy{
    private Long id;
    public DeleteUserStatementStrategy(Long id) {
        this.id = id;
    }
    @Override
    public PreparedStatement makeStatement( Connection connection) throws SQLException {

        String sql = "DELETE FROM userinfo WHERE id=?";


        PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            return preparedStatement;
    }
}
