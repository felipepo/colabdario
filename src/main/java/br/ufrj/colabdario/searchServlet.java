package br.ufrj.colabdario;

import br.ufrj.colabdario.database.databaseDAO;
import br.ufrj.colabdario.dto.newCourseDTO;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig()
public class searchServlet extends HttpServlet {

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
        
        String criteria = jsonObject.getString("criteria");
        String info = jsonObject.getString("info");
        
        ArrayList<newCourseDTO> result = new ArrayList<newCourseDTO>();
        result = (new databaseDAO()).searchCourse(criteria, info);
        
        JSONArray jsonArray = new JSONArray();
        for (int i=0; i < result.size(); i++) {
            System.out.println("Um elemento: "+result.get(i).toString());
            jsonArray.put(result.get(i).toString());
        }
        System.out.println("===========ArrayFinal:"+result.toString());
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result.toString());
        out.flush();
        }
    }