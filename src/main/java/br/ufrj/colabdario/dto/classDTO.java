package br.ufrj.colabdario.dto;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import javax.json.Json;
import javax.json.JsonObject;

public class classDTO implements Serializable {

    public classDTO() {
        this.name = "";
        this.code = "";
        this.start_hour = "";
        this.end_hour = "";
        this.date = "";
    }
    String name;
    String code;
    String start_hour;
    String end_hour;
    String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(String start_hour) {
        this.start_hour = start_hour;
    }

    public String getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(String end_hour) {
        this.end_hour = end_hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
            
    
    
    JsonObject objetoJSON;
    
    public JsonObject toJSON(){
        objetoJSON = Json.createObjectBuilder()
                .add("name", name)
                .add("code", code)
                .add("start_hour", start_hour)
                .add("end_hour", end_hour)
                .add("date", date)
                .build();
        
        return objetoJSON;
    }
    
    @Override
    public String toString(){
        return toJSON().toString();
    }
    
}
