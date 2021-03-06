package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
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

    @Before         //before 은 테스트가 실행되기전 실행
    public void setup(){
        //xml 파일 beans
        GenericXmlApplicationContext context = new GenericXmlApplicationContext(new String[]{"daoFactory.xml"});
        this.userDao =  context.getBean("userDao",UserDao.class);
        //factory beans
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        userDao = context.getBean("userDao",UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {

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

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        String name = "김재현";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        String changedName = "뚱이";
        String changedPassword = "1111";

        user.setId(id);
        user.setName(changedName);
        user.setPassword(changedPassword);

        userDao.update(user);

        User chagedUser = userDao.get(id);

        assertThat(id,is(chagedUser.getId()));
        assertThat(changedName,is(chagedUser.getName()));
        assertThat(changedPassword,is(chagedUser.getPassword()));
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        String name = "김재현";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        userDao.delete(id);

        User deletedUser = userDao.get(id);

        assertThat(deletedUser,nullValue());
    }
}
