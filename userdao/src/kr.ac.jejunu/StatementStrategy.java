package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by blue on 2017-04-18.
 */
public interface StatementStrategy {
    PreparedStatement makeStatement(Object object, Connection connection) throws SQLException;

}
