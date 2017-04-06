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
        return new JejuConnectionMaker();
    }
}
