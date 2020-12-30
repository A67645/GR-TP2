package agent.snmp;

import java.time.LocalDateTime;


public class Snapshot {
    String timestamp;
    private double cpu;
    private double mem;

    public Snapshot (){
        timestamp = LocalDateTime.now().toString();
        cpu = 0.0d;
        mem = 0.0d;
    }

    public Snapshot(String new_dt, double new_cpu, double new_mem){
        timestamp = new_dt;
        cpu = new_cpu;
        mem = new_mem;
    }

    public Snapshot(Snapshot s){
        timestamp = s.getTimestamp();
        cpu = s.getCPU();
        mem = s.getMEM();
    }

    public String getTimestamp(){
        return timestamp;
    }

    public double getCPU(){
        return cpu;
    }

    public double getMEM(){
        return mem;
    }

    public String toString(){

        return "Time: " + timestamp + " CPU: " + cpu + " MEM: " + mem;
    }
}
