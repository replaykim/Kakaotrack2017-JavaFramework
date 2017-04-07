package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by replay on 2017. 4. 7..
 */
public interface StatementStrategy {
    PreparedStatement makeStatement(Connection connection) throws SQLException;

}
