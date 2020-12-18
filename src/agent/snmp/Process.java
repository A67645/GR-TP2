package agent.snmp;

import java.io.FileReader;
import java.io.Reader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.util.ArrayList;

public class Process {
    String pname;
    int pid;
    ArrayList<Snapshot>  usages;

    public Process(){
        pname = "";
        pid = 0;
        usages = new ArrayList<>();
    }

    public Process(String new_name, int new_id, ArrayList<Snapshot> new_usages){
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
        ArrayList<Snapshot> new_usages = new ArrayList<>();
        new_usages.addAll(usages);

        return new_usages;
    }

    public void SaveProcess(String process_name){
        JSONObject obj = new JSONObject();

        obj.put("pname", pname);
        obj.put("pid", pid);

        JSONArray list = new JSONArray();
        for(Snapshot s : usages){
            list.add(s.toJSONString());
        }

        obj.put("messages", list);

        try (FileWriter file = new FileWriter("../../../data/logs/" + process_name + ".json")) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getProcess(String process_name){
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("../../../data/logs/" + process_name + ".json")){

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            pname = (String) jsonObject.get("name");
            pid = (int) jsonObject.get("pid");

            JSONArray jsonArray = (JSONArray) jsonObject.get("usages");
            usages = new ArrayList<>();
            Snapshot s = new Snapshot();

            Iterator<String> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {

                s.fromJSONString(iterator.next());
                usages.add(new Snapshot(s));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
