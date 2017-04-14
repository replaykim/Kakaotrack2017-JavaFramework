package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by replay on 2017. 3. 15..
 *
 * test 는 다돌리는 습관!
 */
public class UserDaoTest {

    UserDao userDao;

    @Before
    public void setup(){
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//        userDao = applicationContext.getBean("userDao", UserDao.class);

        ApplicationContext applicationContext1 = new GenericXmlApplicationContext("DaoFactory.xml");
        userDao = applicationContext1.getBean("userDao",UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        // id 를 주면 이름과 비밀번호를 가져온다.

        Long id = 1l;
        String name  = "김재현";
        String password = "12334";


        User user = userDao.get(id);

        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));

    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        String name =  "뚱이";
        String password = "1234";

        User user = new User();

        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);

        User resultUser = userDao.get(id);

        assertThat(id, is(resultUser.getId()));
        assertThat(name, is(resultUser.getName()));
        assertThat(password, is(resultUser.getPassword()));

    }
}
