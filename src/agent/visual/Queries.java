package agent.visual;


import agent.snmp.*;

import java.io.File;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;

public class Queries {

    ArrayList<Entry> entries;

    public Queries(){
        entries = new ArrayList<>();
    }

    public int indexOfLesserCPU(Entry [] entries_aux){
        int lesser_cpu = 0;

        int i = 0;
        for(Entry e : entries_aux){
            if(entries_aux[lesser_cpu].getCPU() > e.getCPU()){
                lesser_cpu = i;
            }
            i++;
        }

        return lesser_cpu;
    }

    public void topNCPU(int n, LocalDateTime begin, LocalDateTime end){
        Entry [] entries_aux = new Entry[n];
        try {
            File confs = new File("../../../data/logs/");
            String [] filenames = confs.list();
            if(filenames != null) {
                for (String file : filenames) {
                    Processo p = new Processo();
                    p.getProcesso(file + ".json");
                    Entry e = new Entry(p.getPName(), p.getPID(), p.averageCPU(begin, end), p.averageMEM(begin, end));
                    if (entries_aux[indexOfLesserCPU(entries_aux)].getCPU() <= e.getCPU()) {
                        entries_aux[indexOfLesserCPU(entries_aux)] = new Entry(e);
                    }
                }
                entries = new ArrayList<>(Arrays.asList(entries_aux));
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public void topNMEM(int n, LocalDateTime begin, LocalDateTime end){
        Entry [] entries_aux = new Entry[n];
        try {
            File confs = new File("../../../data/logs/");
            String[] filenames = confs.list();
            if(filenames != null) {
                for (String file : filenames) {
                    Processo p = new Processo();
                    p.getProcesso(file + ".json");
                    Entry e = new Entry(p.getPName(), p.getPID(), p.averageMEM(begin, end), p.averageMEM(begin, end));
                    if (entries_aux[indexOfLesserCPU(entries_aux)].getMEM() <= e.getMEM()) {
                        entries_aux[indexOfLesserCPU(entries_aux)] = new Entry(e);
                    }
                }
                entries = new ArrayList<>(Arrays.asList(entries_aux));
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public void zeroesCPU(LocalDateTime begin, LocalDateTime end){
        try {
            File confs = new File("../../../data/logs/");
            String[] filenames = confs.list();
            if(filenames != null) {
                for (String file : filenames) {
                    Processo p = new Processo();
                    p.getProcesso(file + ".json");
                    Entry e = new Entry(p.getPName(), p.getPID(), p.averageMEM(begin, end), p.averageMEM(begin, end));
                    if (e.getCPU() == 0.0d) {
                        entries.add(e);
                    }
                }
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public void zeroesMEM(LocalDateTime begin, LocalDateTime end){
        try {
            File confs = new File("../../../data/logs/");
            String[] filenames = confs.list();
            if(filenames != null) {
                for (String file : filenames) {
                    Processo p = new Processo();
                    p.getProcesso(file + ".json");
                    Entry e = new Entry(p.getPName(), p.getPID(), p.averageMEM(begin, end), p.averageMEM(begin, end));
                    if (e.getMEM() == 0.0d) {
                        entries.add(e);
                    }
                }
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }

}
