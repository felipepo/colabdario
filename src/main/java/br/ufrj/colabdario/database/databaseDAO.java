package br.ufrj.colabdario.database;

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
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
