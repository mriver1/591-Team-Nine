package utils;

import beans.Admin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 9/2/15.
 */
public class LoginUtils {

    private JdbcUtils jdbcUtils;

    public LoginUtils() {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }

    /**
     * Login
     *
     * @param
     * @return
     */
    public String login(Admin admin) {
        // Default result
        String resultStr = "Fail to login";

        String username = null;
        String password = null;
        String sql = null;

        if (admin != null) {
            username = admin.getUsername();
            password = admin.getPassword();
            sql = "select * from admin where username = ? and password = ?";

            List<Object> params = new ArrayList<>();
            params.add(username);
            params.add(password);
            try {
                Admin databaseAdmin = jdbcUtils.findSimpleRefResult(sql, params, Admin.class);
                if (databaseAdmin != null) {
                    resultStr = "SuccessfullyLogin";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }

    /**
     * Register
     *
     * @param
     * @return
     */
    public String register(Admin admin) {
        // Default result
        String resultStr = "Fail to register";

        String username = null;
        String password = null;
        String sql = null;

        if (admin != null) {
            username = admin.getUsername();
            password = admin.getPassword();
            sql = "insert into admin(username,password) values (?,?)";

            List<Object> params = new ArrayList<>();
            params.add(username);
            params.add(password);
            try {
                Boolean updated = jdbcUtils.updateByPreparedStatement(sql, params);
                if (updated != false) {
                    resultStr = "Registered";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }


    /**
     * Logout
     */
    public void logout() {

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (jdbcUtils != null) {
            jdbcUtils.releaseConn();
            jdbcUtils = null;

        }
        System.out.println(this.getClass().toString() + "destroyed.");
    }
}
