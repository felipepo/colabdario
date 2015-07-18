package br.ufrj.colabdario.database;

import br.ufrj.colabdario.dto.classDTO;
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
    //private Connection con;
    
    public signupDTO Login(String login){
        signupDTO result = new signupDTO();
        try
        {
            Connection con = new BaseDAO().getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM  user_table WHERE login = ?");
            pst.setString(1, login);
            ResultSet res = pst.executeQuery();
            result.setLogin(login);
            if (res.next())
            {
                result.setUser_id(Integer.toString(res.getInt("user_id")));
                result.setName(res.getString("name"));
                result.setEmail(res.getString("email"));
                result.setPassword(res.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
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
        
        SimpleDateFormat weekFormat = new SimpleDateFormat("u");
     
        //ONLY USED TO PRINT weekDay
        SimpleDateFormat weekFormat2 = new SimpleDateFormat("EEE");
        
        int nClasses = Integer.parseInt(dto.getnClasses());
        String[] week_day;
        week_day = dto.getWeek_day().split(";");
        int[] int_week_day = new int[nClasses];
        for (int i= 0; i < nClasses; i++){
            if(week_day[i].equals("Segunda")){
                int_week_day[i] = 1;
            }
            if(week_day[i].equals("Terça")){
                int_week_day[i] = 2;
            }
            if(week_day[i].equals("Quarta")){
                int_week_day[i] = 3;
            }
            if(week_day[i].equals("Quinta")){
                int_week_day[i] = 4;
            }
            if(week_day[i].equals("Sexta")){
                int_week_day[i] = 5;
            }
            if(week_day[i].equals("Sábado")){
                int_week_day[i] = 6;
            }
            if(week_day[i].equals("Domingo")){
                int_week_day[i] = 7;
            }
        }
        String[] start_hour;
        start_hour = dto.getStart_hour().split(";");
        String[] end_hour;
        end_hour = dto.getEnd_hour().split(";");
        
        for (java.util.Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            int weekDay = Integer.parseInt( weekFormat.format(date) );
            System.out.println("Interador de dia: "+weekFormat2.format(date));
            for(int i = 0; i< nClasses; i++){
                System.out.println("===Dia da aula: "+week_day[i]);
                if( weekDay == int_week_day[i] ){
                    insertClass(course_id, week_day[i], start_hour[i],end_hour[i], date);
                }
            }
        }
        
    }
    
    public void insertClass(int course_id, String week_day, String start_hour, String end_hour, java.util.Date date){
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
    
    public void insertUserCourse(int course_id, int user_id){
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
    
    public ArrayList<classDTO> classesOfCourse(int course_id){
        ArrayList<classDTO> result = new ArrayList<classDTO>();
        try
        {
            Connection con = new BaseDAO().getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM  class_table WHERE course_id = ?");
            pst.setInt(1, course_id);
            ResultSet res = pst.executeQuery();
            System.out.println("Calendar result query = "+res.toString());
            classDTO dto = new classDTO();
            while (res.next())
            {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                String start_hour = format.format(res.getTime("start_hour"));
                String end_hour = format.format(res.getTime("end_hour"));
                SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
                String date = format2.format(res.getDate("date"));
                String start_hour_final = date+ " " + start_hour;
                String end_hour_final = date+ " " + end_hour;
                dto.setName(res.getString("name"));
                dto.setCode(res.getString("code"));
                dto.setStart_hour(start_hour_final);
                dto.setEnd_hour(end_hour_final);
                dto.setDate(date);
                System.out.println("DTOLoading = " + date);
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try
        {
            Connection con = new BaseDAO().getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM  course_table WHERE course_id = ?");
            pst.setInt(1, course_id);
            ResultSet res = pst.executeQuery();
            String name = "";
            String code = "";
            if (res.next())
            {
                name = res.getString("name");
                code = res.getString("code");
            }
            for(int i = 0; i < result.size(); i++){
                result.get(i).setName(name);
                result.get(i).setCode(code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<classDTO> classesOfUser(int user_id){
        ArrayList<classDTO> result = new ArrayList<classDTO>();
        ArrayList<classDTO> course_results = new ArrayList<classDTO>();
        try
        {
            Connection con = new BaseDAO().getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM  user_course WHERE user_id = ?");
            pst.setInt(1, user_id);
            ResultSet res = pst.executeQuery();
            int course_id = 0;
            while (res.next())
            {
                course_id = res.getInt("course_id");
                course_results = classesOfCourse(course_id);
                result.addAll(course_results);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<newCourseDTO> searchCourse(String criteria, String info){
        ArrayList<newCourseDTO> result = new ArrayList<newCourseDTO>();
        try
        {
            String query = "";
            if (criteria.equals("Nome")){
                query+= "name";
            }
            if (criteria.equals("Código")){
                query+= "code";
            }
            Connection con = new BaseDAO().getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM  course_table WHERE "+query+" = ?");
            pst.setString(1, info);
            ResultSet res = pst.executeQuery();
            System.out.println("=====DataBase: query "+query);
            while (res.next())
            {
                newCourseDTO dto = new newCourseDTO();
                System.out.println("=====DataBase: Name "+res.getString("name"));
                dto.setCourse_id(Integer.toString(res.getInt("course_id")));
                dto.setName(res.getString("name"));
                dto.setCode(res.getString("code"));
                dto.setStart_date("null");
                dto.setEnd_date("null");
                dto.setWeek_day("null");
                dto.setnClasses("null");
                dto.setStart_hour("null");
                dto.setEnd_hour("null");
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
