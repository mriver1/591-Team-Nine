package daos;

import beans.LoginUser;

import java.util.ArrayList;
import java.util.List;

public class LoginDao {

    private DBconnection dBconnection;

    public LoginDao() {
        dBconnection = new DBconnection();
        dBconnection.getConnection();
    }

    public String login(LoginUser admin) {
        // Default result
        String resultStr = "Fail to login";

        String username = null;
        String password = null;
        String sql = null;

        if (admin != null) {
            username = admin.getUsername();
            password = admin.getPassword();
            sql = "select * from admins where username = ? and password = ?";

            List<Object> params = new ArrayList<>();
            params.add(username);
            params.add(password);
            try {
                LoginUser databaseAdmin = dBconnection.findSimpleRefResult(sql, params, LoginUser.class);
                if (databaseAdmin != null) {
                    resultStr = "SuccessfullyLogin";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }

    public String register(LoginUser admin) {
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
                Boolean updated = dBconnection.updateByPreparedStatement(sql, params);
                if (updated != false) {
                    resultStr = "Registered";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (dBconnection != null) {
            dBconnection.releaseConn();
            dBconnection = null;
        }

        System.out.println(this.getClass().toString() + "destroyed.");
    }
}
