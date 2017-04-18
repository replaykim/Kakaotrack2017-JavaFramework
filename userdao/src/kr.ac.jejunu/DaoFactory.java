package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by blue on 2017-04-18.
 */
@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao(){
        return  new UserDao(getJejuConnectionMaker());
    }

    @Bean
    public ConnetionMaker getJejuConnectionMaker(){
        return new JejuConnectionMaker();
    }
}
