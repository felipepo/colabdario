package br.ufrj.colabdario;

import br.ufrj.colabdario.database.databaseDAO;
import br.ufrj.colabdario.dto.newCourseDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig()
public class newCourseServlet extends HttpServlet {

    @Override
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {
        BufferedReader br = new BufferedReader(new  InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
       
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = reader.readObject();
        reader.close();
        
        newCourseDTO dto = new newCourseDTO();
        dto.setName(jsonObject.getString("name"));
        dto.setCode(jsonObject.getString("code"));
        dto.setStart_date(jsonObject.getString("start_date"));
        dto.setEnd_date(jsonObject.getString("end_date"));
        dto.setWeek_day(jsonObject.getString("week_day"));
        dto.setnClasses(jsonObject.getString("nClasses"));
        dto.setStart_hour(jsonObject.getString("start_hour"));
        dto.setEnd_hour(jsonObject.getString("end_hour"));
        
        try{
            (new databaseDAO()).insertCourse(dto);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(dto.toString());
        out.flush();
        }
    }