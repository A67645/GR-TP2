package agent.snmp;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Scanner;

public class Snapshot {
    LocalDateTime timestamp;
    private double cpu;
    private double mem;

    public Snapshot (){
        timestamp = LocalDateTime.now();
        cpu = 0.0d;
        mem = 0.0d;
    }

    public Snapshot(LocalDateTime new_dt, double new_cpu, double new_mem){
        timestamp = new_dt;
        cpu = new_cpu;
        mem = new_mem;
    }

    public Snapshot(Snapshot s){
        timestamp = s.getTimestamp();
        cpu = s.getCPU();
        mem = s.getMEM();
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public double getCPU(){
        return cpu;
    }

    public double getMEM(){
        return mem;
    }

    public void fromJSONString(String jsonString){
        JSONParser parser = new JSONParser();

        try {

            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);

            String ldt = (String) jsonObject.get("timestamp");
            timestamp = LocalDateTime.parse(ldt);


            cpu = (double) jsonObject.get("cpu");

            mem = (double) jsonObject.get("mem");
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String toJSONString(){
        JSONObject obj = new JSONObject();

        obj.put("timestamp", timestamp.toString());
        obj.put("cpu", cpu);
        obj.put("mem", mem);
        return obj.toJSONString();

    }
}
