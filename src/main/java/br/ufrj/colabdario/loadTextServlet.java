/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.colabdario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

/**
 *
 * @author lyang
 */
@MultipartConfig()
public class loadTextServlet extends HttpServlet {

 
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
       
        JsonObject jsonObject;
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            jsonObject = reader.readObject();
        }
        
        //String file = jsonObject.getString("file");
        System.out.println("CLASS NUMBER LIDO================");
        //File arquivo = new File("/home/lyang/Documents/PA_test/"+file);
        File arquivo = new File("/home/lyang/Documents/PA_test/filedata.txt");
        
        FileReader fr = new FileReader(arquivo);
 
        BufferedReader sp = new BufferedReader(fr);
        String text = null;
        //equanto houver mais linhas
        while (sp.ready()) {
        //lê a proxima linha
            String linha = sp.readLine();
            text+=linha;
        }
 
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(text);
        out.flush();
        }

}
