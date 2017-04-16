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

    public User get(Long id) throws ClassNotFoundException, SQLException {
        //User 어디에있어? Mysql
        //Class 를 로딩해야되겠네.
        Connection connection = getConnection();
        //쿼리를만들어야겠네
        PreparedStatement preparedStatement = connection.prepareStatement("select * from userdata where id = ?");
        preparedStatement.setLong(1, id);
        //쿼리를실행해야겠네
        ResultSet resultSet = preparedStatement.executeQuery();
        //실행된결과를 객체에매핑
        resultSet.next();                               //커서를 욺긴다
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과를 리턴



        return user;
    }


    public void add(User user) throws ClassNotFoundException, SQLException {
        //User 어디에있어? Mysql
        //Class 를 로딩해야되겠네.
        Connection connection = getConnection();
        //쿼리를만들어야겠네
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userdata VALUES (?,?,?)");
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2,user.getName());
        preparedStatement.setString(3,user.getPassword());
        //쿼리를실행해야겠네
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://117.17.102.106:3306/replayDB?characterEncoding=utf-8", "root", "1234");
    }
}
