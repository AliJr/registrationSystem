package DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBase {
private boolean connected=false;
private Connection conn;

public DBase(String username,String password) {
  
 try {
   conn = DriverManager.getConnection("jdbc:oracle:thin:@coestudb.qu.edu.qa:1521/STUD.qu.edu.qa",username, password);
   connected=true;
 } catch (SQLException e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 }
}
public void addStudent(String name,String gender,String mobile, Date date, int nationalityID, int departmentID){
    try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*)FROM STUDENT");
        rs.next();
        int studentID=rs.getInt(1)+1;
        rs.close();
        stmt.close();
        PreparedStatement st=conn.prepareStatement("insert into emp(student_id,student_name,gender,mobile,"
                + "birth_date,nationality_id,Department_id) values(?,?,?,?,?,?,?)");
        st.setInt(1,studentID);
        st.setString(2,name);
        st.setString(3,gender);
        st.setString(4,mobile);
        st.setDate(5,date);
        st.setInt(6,nationalityID);
        st.setInt(7,departmentID);
        st.executeUpdate();
        

        
    } catch (SQLException ex) {
        Logger.getLogger(DBase.class.getName()).log(Level.SEVERE, null, ex);
    }
                    
}

public boolean checkConnection(){
    return connected;
}
}