package br.ufrj.colabdario;

import br.ufrj.colabdario.database.databaseDAO;
import br.ufrj.colabdario.dto.classDTO;
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
public class calendarServlet extends HttpServlet {

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
        
        String string_user_id = jsonObject.getString("user_id");
        int user_id = Integer.parseInt(string_user_id);
        
        ArrayList<classDTO> result = new ArrayList<classDTO>();
        result = (new databaseDAO()).classesOfUser(user_id);
        
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