package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by replay on 2017. 3. 24..
 */
public class JejuConnectionMaker implements ConnectionMaker {

    private String url ;
    private String id ;
    private String password ;
    private String className;


    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName(this.className);
        return DriverManager.getConnection(this.url, this.id, this.password);

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
