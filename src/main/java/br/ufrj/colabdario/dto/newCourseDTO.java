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
    String week_day;
    String nClasses;
    String start_hour;
    String end_hour;

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

    public String getWeek_day() {
        return week_day;
    }

    public void setWeek_day(String week_day) {
        this.week_day = week_day;
    }

    public String getnClasses() {
        return nClasses;
    }

    public void setnClasses(String nClasses) {
        this.nClasses = nClasses;
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
        
    
    
    JsonObject objetoJSON;
    
    public JsonObject toJSON(){
        objetoJSON = Json.createObjectBuilder()
                .add("name", name)
                .add("code", code)
                .add("start_date", start_date)
                .add("end_date", end_date)
                .add("week_day", week_day)
                .add("nClasses", nClasses)
                .add("start_hour", start_hour)
                .add("end_hour", end_hour)
                .build();
        
        return objetoJSON;
    }
    
    @Override
    public String toString(){
        return toJSON().toString();
    }
    
}
