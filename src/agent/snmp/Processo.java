package agent.snmp;

import java.io.FileReader;
import java.io.Reader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

import java.time.LocalDateTime;

public class Processo {
    String pname;
    int pid;
    ArrayList<Snapshot>  usages;

    public Processo(){
        pname = "";
        pid = 0;
        usages = new ArrayList<>();
    }

    public Processo(String new_name, int new_id, ArrayList<Snapshot> new_usages){
        pname = new_name;
        pid = new_id;
        usages = new ArrayList<>();

        usages.addAll(new_usages);
    }

    public String getPName(){
        return pname;
    }

    public int getPID(){
        return pid;
    }

    public ArrayList<Snapshot> getUsages(){
        return new ArrayList<>(usages);

    }

    public void SaveProcesso(String processo_name){
        JSONObject obj = new JSONObject();

        obj.put("pname", pname);
        obj.put("pid", pid);

        JSONArray list = new JSONArray();
        for(Snapshot s : usages){
            list.put(s.toJSONString());
        }

        obj.put("usages", list);

        try (FileWriter file = new FileWriter("../../../data/logs/" + processo_name + ".json")) {
            file.write(obj.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getProcesso(String processo_name){
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("../../../data/logs/" + processo_name + ".json")){

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            pname = (String) jsonObject.get("name");
            pid = (int) jsonObject.get("pid");

            JSONArray jsonArray = (JSONArray) jsonObject.get("usages");
            usages = new ArrayList<>();
            Snapshot s = new Snapshot();

            for(Object o : jsonArray){

                s.fromJSONString(o.toString());
                usages.add(new Snapshot(s));
            }
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public double averageCPU(LocalDateTime begin, LocalDateTime end){
        double sum = 0.0d;

        for(Snapshot s : usages){
            if(begin.isBefore(s.getTimestamp()) && end.isAfter(s.getTimestamp())){
                sum += s.getCPU();
            }
        }

        return sum/(usages.size());
    }

    public double averageMEM(LocalDateTime begin, LocalDateTime end){
        double sum = 0.0d;

        for(Snapshot s : usages){
            if(begin.isBefore(s.getTimestamp()) && end.isAfter(s.getTimestamp())){
                sum += s.getCPU();
            }
        }

        return sum/(usages.size());
    }

    public LocalDateTime getLast(){
        int last_index = usages.size() - 1;
        return usages.get(last_index).getTimestamp();
    }
}
