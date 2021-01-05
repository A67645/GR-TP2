package agent.visual;

public class Entry {
    private String pname;
    private int pid;
    private double cpu;
    private double mem;

    public Entry(){
        pname = "";
        pid = 0;
        cpu = 0.0d;
        mem = 0.0d;
    }

    public Entry(String new_pname, int new_pid, double new_cpu, double new_mem){
        pname = new_pname;
        pid = new_pid;
        cpu = new_cpu;
        mem = new_mem;
    }

    public Entry(Entry e){
        pname = e.getPName();
        pid = e.getPID();
        cpu = e.getCPU();
        mem = e.getMEM();
    }

    public String getPName(){
        return pname;
    }

    public int getPID(){
        return pid;
    }

    public double getCPU(){
        return cpu;
    }

    public double getMEM(){
        return mem;
    }

    public void setPName(String new_pname){
        pname = new_pname;
    }

    public void setPID(int new_pid){
        pid = new_pid;
    }

    public void setCPU(double new_cpu){
        cpu = new_cpu;
    }

    public void setMEM(double new_mem){
        mem = new_mem;
    }

    public String toString(){
        return "Process Name = " + pname + "\nPID = " + pid + "\nCPU usage = " + cpu + "\nMemory Usage = " + mem;
    }

}
