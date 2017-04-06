package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by replay on 2017. 3. 24..
 */

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());

        return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        JejuConnectionMaker connectionMaker = new JejuConnectionMaker();
        connectionMaker.setPassword("1234");
        connectionMaker.setClassName("com.mysql.jdbc.Driver");
        connectionMaker.setUrl("jdbc:mysql://117.17.102.106:3306/replayDB?characterEncoding=utf-8");
        connectionMaker.setId("root");
        return connectionMaker;
    }
}
