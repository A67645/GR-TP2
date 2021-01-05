package agent.snmp;

import agent.alarms.*;

import java.io.File;
import java.io.IOException;

import agent.alarms.Processo_Alarms;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

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
        usages = new ArrayList<>(new_usages);
    }

    public String getPName(){
        return pname;
    }

    public int getPID(){
        return pid;
    }

    public ArrayList<Snapshot> getUsages(){
        return new ArrayList<Snapshot>(usages);

    }

    public void addSnapshot(Snapshot s){
        Processo_Alarms pa = new Processo_Alarms(pname, pid, new ArrayList<>());
        if(new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/alarms/" + pname + "_" + pid + ".json").exists() == false) pa.saveProcessoAlarms();
        pa.loadProcessoAlarms(pname + "_" + pid + ".json");

        if(s.getCPU() >= 20){
            Alarm a = new Alarm("CPU overload", s.getTimestamp());
            pa.addAlarm(a);
            pa.saveProcessoAlarms();
        }
        if(s.getMEM() >= 20){
            Alarm a = new Alarm("MEM overload", s.getTimestamp());
            pa.addAlarm(a);
            pa.saveProcessoAlarms();
        }
        usages.add(s);
    }

    public void saveProcesso(){
        ObjectMapper mapper = new ObjectMapper();

        try{
            File json = new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/logs/" + pname + "_" + pid + ".json");
            Processo p = new Processo(pname, pid, usages);
            mapper.writeValue(json, p);
        }
        catch (JsonGenerationException ge) {
            ge.printStackTrace();
        }
        catch (JsonMappingException me) {
            me.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void loadProcesso(String filename){
        ObjectMapper mapper = new ObjectMapper();

        try{
            File json = new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/logs/" + filename);
            Processo p = mapper.readValue(json, Processo.class);
            pname = p.getPName();
            pid = p.getPID();
            usages = new ArrayList<Snapshot>(p.getUsages());
        }
        catch (JsonGenerationException ge) {
            ge.printStackTrace();
        }
        catch (JsonMappingException me) {
            me.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public double averageCPU(LocalDateTime begin, LocalDateTime end){
        double sum = 0.0d;

        for(Snapshot s : usages){
            if(begin.isBefore(LocalDateTime.parse(s.getTimestamp())) && end.isAfter(LocalDateTime.parse(s.getTimestamp()))){
                sum += s.getCPU();
            }
        }

        return sum/(usages.size());
    }

    public double averageMEM(LocalDateTime begin, LocalDateTime end){
        double sum = 0.0d;

        for(Snapshot s : usages){
            if(begin.isBefore(LocalDateTime.parse(s.getTimestamp())) && end.isAfter(LocalDateTime.parse(s.getTimestamp()))){
                sum += s.getCPU();
            }
        }

        return sum/(usages.size());
    }
}
