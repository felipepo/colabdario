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
    
    public void insertUser(signupDTO dto){
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
    
    public void insertCourse(newCourseDTO dto){
        try{
            Connection con = new BaseDAO().getConnection();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        int course_id = 0;
        
        try
        {
            Connection con2 = new BaseDAO().getConnection();
            Statement st = con2.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM  course_table");
            while (res.next())
            {
                course_id = res.getInt("course_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        insertUserCourse(course_id, 1);
        
        int nClasses = Integer.parseInt(dto.getnClasses());
        String[] week_day;
        week_day = dto.getWeek_day().split(";");
        String[] start_hour;
        start_hour = dto.getStart_hour().split(";");
        String[] end_hour;
        end_hour = dto.getEnd_hour().split(";");
        for(int i = 0; i< nClasses; i++){
            insertClass(course_id, week_day[i], start_hour[i],end_hour[i]);
        }
        
    }
    
    void insertClass(int course_id, String week_day, String start_hour, String end_hour){
        try{
            Connection con = new BaseDAO().getConnection();
            PreparedStatement pstmt = con.prepareStatement(
            "INSERT INTO class_table (course_id, week_day,start_hour,end_hour)"
                    + "VALUES (?,?,?,?);");
            pstmt.setInt(1, course_id);
            pstmt.setString(2, week_day);
            java.util.Date date1 = new SimpleDateFormat("HH:mm").parse(start_hour);
            System.out.println(date1.toString());
            java.sql.Time time1 = new java.sql.Time(date1.getTime());
            System.out.println(time1.toString());
            pstmt.setTime(3,time1);
            java.util.Date date2 = new SimpleDateFormat("HH:mm").parse(end_hour); 
            java.sql.Time time2 = new java.sql.Time(date2.getTime());
            pstmt.setTime(4,time2);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void insertUserCourse(int course_id, int user_id){
        try{
            Connection con = new BaseDAO().getConnection();
            PreparedStatement pstmt = con.prepareStatement(
            "INSERT INTO user_course (course_id,user_id) VALUES (?,?);");
            pstmt.setInt(1, course_id);
            pstmt.setInt(2, user_id);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
