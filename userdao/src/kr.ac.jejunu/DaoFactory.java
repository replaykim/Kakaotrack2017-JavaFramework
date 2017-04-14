package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by replay on 2017. 4. 14..
 */

    @Configuration
public class DaoFactory {

        @Bean
    public UserDao userDao() {
        return new UserDao(getConnectionMaker());
    }

    @Bean
    public ConnectionMaker getConnectionMaker(){
            return new JejuConnectionMaker();
    }
}
