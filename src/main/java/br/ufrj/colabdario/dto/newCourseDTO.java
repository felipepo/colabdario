package br.ufrj.colabdario.dto;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import javax.json.Json;
import javax.json.JsonObject;

public class newCourseDTO implements Serializable {
    String name;
    String code;
    String start_date;
    String end_date;

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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
        
    
    
    JsonObject objetoJSON;
    
    public JsonObject toJSON(){
        objetoJSON = Json.createObjectBuilder()
                .add("name", name)
                .add("code", code)
                .add("start_date", start_date)
                .add("end_date", end_date)
                .build();
        
        return objetoJSON;
    }
    
    @Override
    public String toString(){
        return toJSON().toString();
    }
    
}
