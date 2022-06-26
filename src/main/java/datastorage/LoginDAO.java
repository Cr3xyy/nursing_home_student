package datastorage;

import model.Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAO extends DAOimp<Login> {

    public LoginDAO(Connection conn) {
        super(conn);
    }



    @Override
    protected String getCreateStatementString(Login login) {
        return null;
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caregiver WHERE cid = %d", key);
    }



    @Override
    protected Login getInstanceFromResultSet(ResultSet set) throws SQLException {
        return null;
    }

    @Override
    protected String getReadAllStatementString() {
        return null;
    }

    @Override
    protected ArrayList<Login> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<Login> list = new ArrayList<Login>();
        Login l = null;
        while (set.next()) {
            l = new Login(set.getString(1), set.getString(2));
            list.add(l);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Login login) {
        return null;
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return null;
    }

}
