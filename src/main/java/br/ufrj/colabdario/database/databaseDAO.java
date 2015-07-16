package br.ufrj.colabdario.database;

import br.ufrj.colabdario.dto.newCourseDTO;
import br.ufrj.colabdario.dto.signupDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class databaseDAO extends BaseDAO {
    private Connection con;
    
    public void newUser(signupDTO dto){
        try{
            Connection con = new BaseDAO().getConnection();
            System.out.println(dto.getName());
            PreparedStatement pstmt = con.prepareStatement(
            "INSERT INTO user_table (name,email,login,password)"
                    + "VALUES (?,?,?,?);");
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getEmail());
            pstmt.setString(3, dto.getLogin());
            pstmt.setString(4, dto.getPassword());
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void newCourse(newCourseDTO dto){
        try{
            Connection con = new BaseDAO().getConnection();
            System.out.println(dto.getName());
            PreparedStatement pstmt = con.prepareStatement(
            "INSERT INTO course_table (name,code,start_date,end_date)"
                    + "VALUES (?,?,?,?);");
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getCode());
            String start_date = dto.getStart_date();
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");  
            java.sql.Date date1 = new java.sql.Date( ((java.util.Date)format1.parse(start_date)).getTime() ); 
            pstmt.setDate(3,date1);
            String end_date = dto.getEnd_date();
            SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");  
            java.sql.Date date2 = new java.sql.Date( ((java.util.Date)format2.parse(end_date)).getTime() ); 
            pstmt.setDate(4,date2);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
            //THIS FUNCTION WILL ALSO INSERT LINES ON THE CLASSES TABLE
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
