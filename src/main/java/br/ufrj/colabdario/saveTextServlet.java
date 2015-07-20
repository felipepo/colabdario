
package br.ufrj.colabdario;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
public class saveTextServlet extends HttpServlet {

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
        
        String text = jsonObject.getString("text");
        System.out.println("TEXT LIDO================");
        File arquivo = new File("/home/lyang/Documents/PA_test/filedata.txt");
        FileWriter fw = new FileWriter(arquivo, true);
 
        BufferedWriter bw = new BufferedWriter(fw);
 
        bw.write(text);

        bw.newLine();

        bw.close();
        fw.close();
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.flush();
        }
    }