package kr.ac.jejunu;

/**
 * Created by replay on 2017. 4. 14..
 */
public class DaoFactory {

    public UserDao getJejuDao() {
        return new UserDao(new JejuConnectionMaker());
    }
}
