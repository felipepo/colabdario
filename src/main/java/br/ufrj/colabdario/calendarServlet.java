<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.colabdario;

=======
package br.ufrj.colabdario;

import br.ufrj.colabdario.database.databaseDAO;
import br.ufrj.colabdario.dto.classDTO;
import org.json.JSONArray;
>>>>>>> 1503be6d87d16527ccfc8af54bfddd99730e104e
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
<<<<<<< HEAD
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.ufrj.colabdario.dto.classDTO;
import br.ufrj.colabdario.database.databaseDAO;
import java.util.ArrayList;

/**
 *
 * @author matheus
 */
public class calendarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("====== Initializing calendar Servlet");
            /* TODO output your page here. You may use following sample code. */
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String json = "";
            if (br != null) {
                json = br.readLine();
            }
            System.out.println("Object Json Read = " + json);
            JsonReader reader = Json.createReader(new StringReader(json));
            JsonObject jsonObject = reader.readObject();
            reader.close();
            
            int userId = jsonObject.getInt("userId");
            System.out.println("Extracted user ID = "+userId);
            //int userId =1;
            //Creating database DAO.
            databaseDAO dao = new databaseDAO();
            ArrayList dtoArray;
            //Getting all the classes related to the user.
            dtoArray = dao.classesOfUser(userId);
            System.out.println("Number of classes loaded = " +dtoArray.size());
            String responseArray = "[";
            //Loading the response string
            for(int i = 0; i < dtoArray.size()-1;i++){
//
//                System.out.println(responseArray);
//                System.out.println(responseArray);
//                System.out.println(responseArray);
                responseArray += dtoArray.get(i).toString()+",";
            }
            System.out.println(responseArray);
            responseArray += dtoArray.get(dtoArray.size()-1) + "]";
            //Sending the response to client.
            response.setContentType("application/json;charset=UTF-8");
            out.print(responseArray);
            out.flush();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
=======
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
>>>>>>> 1503be6d87d16527ccfc8af54bfddd99730e104e
