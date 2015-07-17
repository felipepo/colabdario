package br.ufrj.colabdario.database;

import br.ufrj.colabdario.dto.newCourseDTO;
import br.ufrj.colabdario.dto.signupDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class databaseDAO extends BaseDAO {
    private Connection con;
    
    public void insertUser(signupDTO dto){
        try{
            Connection con = new BaseDAO().getConnection();
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
    
    public void insertCourse(newCourseDTO dto) {
        
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        
        String start_date = dto.getStart_date();
        java.util.Date util_start_date = null;
        String end_date = dto.getEnd_date();
        java.util.Date util_end_date = null;
        try {
            util_start_date = format1.parse(start_date);
            util_end_date = format1.parse(end_date);
        } catch (ParseException ex) {
            Logger.getLogger(databaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sql_start_date = new java.sql.Date( util_start_date.getTime() );
        java.sql.Date sql_end_date = new java.sql.Date( util_end_date.getTime() ); 
        
        Calendar start = Calendar.getInstance();
        start.setTime(util_start_date);
        Calendar end = Calendar.getInstance();
        end.setTime(util_end_date);
        
        //start e end 1(segunda) até 7(domingo)
        SimpleDateFormat format2 = new SimpleDateFormat("u");
        int start_day = Integer.parseInt(format2.format(sql_start_date));
        int end_day = Integer.parseInt(format2.format(sql_end_date));
        
        try{
            Connection con = new BaseDAO().getConnection();
            PreparedStatement pstmt = con.prepareStatement(
            "INSERT INTO course_table (name,code,start_date,end_date)"
                    + "VALUES (?,?,?,?);");
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getCode());
            pstmt.setDate(3,sql_start_date);
            pstmt.setDate(4,sql_end_date);
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
        
        SimpleDateFormat weekFormat = new SimpleDateFormat("EEE");
        
        int nClasses = Integer.parseInt(dto.getnClasses());
        String[] week_day;
        week_day = dto.getWeek_day().split(";");
        String[] start_hour;
        start_hour = dto.getStart_hour().split(";");
        String[] end_hour;
        end_hour = dto.getEnd_hour().split(";");
        
        for (java.util.Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            String weekDay = weekFormat.format(date);
            System.out.println("Interador de dia: "+weekDay);
            for(int i = 0; i< nClasses; i++){
                String class_week_day = week_day[i].substring(0, Math.min(week_day[i].length(), 3));
                System.out.println("===Dia da aula: "+class_week_day);
                if( weekDay.equals(class_week_day) ){
                    insertClass(course_id, week_day[i], start_hour[i],end_hour[i], date);
                }
            }
        }
        
    }
    
    void insertClass(int course_id, String week_day, String start_hour, String end_hour, java.util.Date date){
        try{
            Connection con = new BaseDAO().getConnection();
            PreparedStatement pstmt = con.prepareStatement(
            "INSERT INTO class_table (course_id, week_day,start_hour,end_hour, date)"
                    + "VALUES (?,?,?,?,?);");
            pstmt.setInt(1, course_id);
            pstmt.setString(2, week_day);
            java.util.Date date1 = new SimpleDateFormat("HH:mm").parse(start_hour);
            java.sql.Time time1 = new java.sql.Time(date1.getTime());
            pstmt.setTime(3,time1);
            java.util.Date date2 = new SimpleDateFormat("HH:mm").parse(end_hour); 
            java.sql.Time time2 = new java.sql.Time(date2.getTime());
            pstmt.setTime(4,time2);
            java.sql.Date sql_date = new java.sql.Date(date.getTime());
            pstmt.setDate(5,sql_date);
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
