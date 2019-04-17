package sample.database;
import sample.Client;
import sample.Employee;
import sample.Globals;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String SELECT_EMPLOYEE = "Select Employee.id, lname, fname, position, login FROM Employee "+
            " INNER JOIN LoginEmp ON ide=Employee.id WHERE login LIKE ? AND password LIKE ?";

    private String SELECT_CLIENTS = "Select Client.id, lname, fname, email, login FROM Client "+
            " INNER JOIN LoginClient ON idc=Client.id "+
            " ORDER BY lname, fname";

    private static Database database;

    static {
        database = new Database();
    }

    private Database(){
    }
    public static Database getInstance(){
        return database;
    }

    private Connection getConnection(){
        Connection conn;
        try {
            Class.forName(Globals.databClassforName);
            System.out.println("Driver was successfully loaded.");
            conn=DriverManager.getConnection(Globals.url, Globals.username, Globals.password);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection to database failed");
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection conn){
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Employee getEmployee(String login, String password) {
        Connection conn =getConnection();
        if(conn!=null){
            try {
                PreparedStatement ps = conn.prepareStatement(SELECT_EMPLOYEE);
                ps.setString(1, login);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Employee emp=new Employee(rs.getString("fname"),rs.getString("lname"), rs.getString("login"),rs.getInt("position"), rs.getInt("id"));
                    closeConnection(conn);
                    return emp;
                }


            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Client> getAllClients(){
        Connection conn =getConnection();

        if(conn!=null){
            List list=new ArrayList<>();
            try {
                PreparedStatement ps = conn.prepareStatement(SELECT_CLIENTS);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    Client client=new Client(rs.getString("fname"),rs.getString("lname"),rs.getString("login"),rs.getInt("id"));
                    list.add(client);
                }
                closeConnection(conn);
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
