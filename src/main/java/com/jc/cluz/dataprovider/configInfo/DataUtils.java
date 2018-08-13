package com.jc.cluz.dataprovider.configInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by honglb on 2018/7/5.
 */
@Configuration
public class DataUtils {
    @Autowired
    ConfigBean configBean;
//    // 数据库地址
//    @Value("${spring.datasource.url}")
//    private String url;
//    // 数据库用户名
//    @Value("${spring.datasource.username}")
//    private String username;
//    // 数据库密码
//    @Value("${spring.datasource.password}")
//    private String password;
//    // 驱动名称
//    @Value("${spring.datasource.driver-class-name}")
//    private String driver;

//    public DataUtils(String url, String username, String password, String driver){
//        // 设定各项参数，包括数据库连接、驱动类型
//        this.url = url;
//        this.username = username;
//        this.password = password;
//        this.driver = driver;
//    }

    public Connection connectDb(){
        Connection conn = null;
        /**
         * Created by honglb on 2018/7/6.
         * 从配置文件获取数据库相关配置
         */
        String driver = configBean.getDriver();
        String url = configBean.getUrl();
        String username = configBean.getUsername();
        String passwrod = configBean.getPassword();
        try{
            // 注册 JDBC 驱动
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(driver);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(url, username, passwrod);
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库连接成功！！");
        }
        return conn;
    }

    /**
     * Created by honglb on 2018/7/6.
     *数据库表内容查询
     */
    public List<String> dbSearch(String sql){
        Statement stmt = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        //DataUtils dataUtils= new DataUtils();
        // 执行查询
        System.out.println(" 实例化Statement对象...");
        try {
            stmt = connectDb().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int liecount = rsmd.getColumnCount();
            while (rs.next()) {
                for(int i=1; i<=liecount; i++) {
                    // 将查询出的内容添加到list中，其中userName为数据库中的字段名称
                    list.add(rs.getString(i));
//                  list.add(rs.getString("username"));
//                  list.add(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
                try {
                    if(stmt != null) {
                        stmt.close();
                    }
                    if(rs != null){
                        rs.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        return list;
    }
}
