package agent.snmp;

import java.io.File;
import java.io.IOException;

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

    public void addSnapshot(Snapshot s){
        usages.add(s);
    }

    public void saveProcesso(){
        ObjectMapper mapper = new ObjectMapper();

        try{
            File json = new File("C:\\Users\\thech\\Desktop\\MIEI\\GR\\GR-TP2\\data\\logs\\" + pname + ".json");
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

    public void loadProcesso(String processo_name){
        ObjectMapper mapper = new ObjectMapper();

        try{
            File json = new File("C:\\Users\\thech\\Desktop\\MIEI\\GR\\GR-TP2\\data\\logs\\" + processo_name + ".json");
            Processo p = mapper.readValue(json, Processo.class);
            pname = p.getPName();
            pid = p.getPID();
            usages = new ArrayList<Snapshot>(usages);
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
