package kr.ac.jejunu;

/**
 * Created by blue on 2017-04-18.
 */
public class DaoFactory {

    public UserDao getJejuDao(){
        return  new UserDao(new HallaConnectionMaker());
    }
}
