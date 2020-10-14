package pizzeria;
import java.sql.*;

public class Database {
    private Connection connec;

    public Connection getConnec(){
        return this.connec;
    }

    public void setConnec(Connection connec){
        this.connec = connec;
    }
    public Database(String link, String user, String pwd){
        try{
            System.out.println("Bonjour");
            Class.forName("com.mysql.jdbc.Driver");
            setConnec( DriverManager.getConnection(link, user, pwd));
            Statement stmt = connec.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pizza");
            while(rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }


}
