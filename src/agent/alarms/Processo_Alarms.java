package agent.alarms;

import agent.snmp.Config;
import agent.snmp.Processo;
import agent.snmp.Snapshot;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import java.util.ArrayList;

public class Processo_Alarms {
    private String pname;
    private int pid;
    private ArrayList<Alarm> alarms;

    public Processo_Alarms(){
        pname =  "";
        pid = 0;
        alarms = new ArrayList<>();
    }

    public Processo_Alarms(String new_pname, int new_pid, ArrayList<Alarm> new_alarms){
        pname = new_pname;
        pid = new_pid;
        alarms = new ArrayList<Alarm>(new_alarms);
    }

    public Processo_Alarms(Processo_Alarms pa){
        pname = pa.getPName();
        pid = pa.getPID();
        alarms = pa.getAlarms();
    }

    public String getPName (){return pname;}

    public int getPID(){return pid;}

    public ArrayList<Alarm> getAlarms(){return new ArrayList<Alarm>(alarms);}

    public void setPName(String new_pname){
        pname = new_pname;
    }

    public void setPID(int new_pid){
        pid = new_pid;
    }

    public void setAlarms(ArrayList<Alarm> new_alarms){
        alarms = new ArrayList<Alarm>(new_alarms);
    }

    public void addAlarm(Alarm a){alarms.add(a);}

    public void loadProcessoAlarms(String filename){
        ObjectMapper mapper = new ObjectMapper();

        try{
            File json = new File("C:\\Users\\thech\\Desktop\\MIEI\\GR\\GR-TP2\\data\\alarms\\" + filename);
            Processo_Alarms pa = mapper.readValue(json, Processo_Alarms.class);
            pname = pa.getPName();
            pid = pa.getPID();
            alarms = new ArrayList<Alarm>(pa.getAlarms());
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

    public void saveProcessoAlarms(){
        ObjectMapper mapper = new ObjectMapper();

        try{
            File json = new File("C:\\Users\\thech\\Desktop\\MIEI\\GR\\GR-TP2\\data\\alarms\\" + pname + "_" + pid + ".json");
            Processo_Alarms p = new Processo_Alarms(pname, pid, alarms);
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
}
