package br.ufrj.colabdario.dto;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import javax.json.Json;
import javax.json.JsonObject;

public class signupDTO implements Serializable {
    String name;
    String email;
    String login;
    String password;
        
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    JsonObject objetoJSON;
    
    public JsonObject toJSON(){
        objetoJSON = Json.createObjectBuilder()
                .add("name", name)
                .add("email", email)
                .add("login", login)
                .add("password", password)
                .build();
        
        return objetoJSON;
    }
    
    @Override
    public String toString(){
        return toJSON().toString();
    }
    
}
