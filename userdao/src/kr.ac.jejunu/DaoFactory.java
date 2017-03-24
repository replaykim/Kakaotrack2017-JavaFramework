package kr.ac.jejunu;

/**
 * Created by replay on 2017. 3. 24..
 */
public class DaoFactory {
    public UserDao getUserDao() {
        return new UserDao(new JejuConnectionMaker());
    }
}
