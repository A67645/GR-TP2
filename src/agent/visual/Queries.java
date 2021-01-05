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

        for(int i = 0; i < entries_aux.length; i++){
            if(entries_aux[lesser_cpu].getCPU() > entries_aux[i].getCPU()){
                lesser_cpu = i;
            }
            i++;
        }

        return lesser_cpu;
    }

    public void topNCPU(int n, LocalDateTime begin, LocalDateTime end){
        Entry [] entries_aux = new Entry[n];
        try {
            File confs = new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/logs/");
            String [] filenames = confs.list();
            if(filenames.length < n) n = filenames.length;
            if(filenames.length > 0) {
                for (int i = 0; i < n; i++) {
                    Processo p = new Processo();
                    p.loadProcesso(filenames[i]);
                    Entry e = new Entry(p.getPName(), p.getPID(), p.averageCPU(begin, end), p.averageMEM(begin, end));
                    if (entries_aux[i] != null && entries_aux[indexOfLesserCPU(entries_aux)].getCPU() <= e.getCPU()) {
                        entries_aux[indexOfLesserCPU(entries_aux)] = new Entry(e);
                    }
                    else if(entries_aux[i] == null){
                        entries_aux[i] = new Entry(e);
                    }
                }
                entries = new ArrayList<>(Arrays.asList(entries_aux));
                for(int i = 0; i<entries.size(); i++){
                    System.out.println(i + " -> " + entries.get(i).toString());
                }
            }
        }
        catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }

    public void topNMEM(int n, LocalDateTime begin, LocalDateTime end){
        Entry [] entries_aux = new Entry[n];
        try {
            File confs = new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/logs/");
            String[] filenames = confs.list();
            if(filenames.length < n) n = filenames.length;
            if(filenames.length > 0) {
                for (int i = 0; i < filenames.length; i++) {
                    Processo p = new Processo();
                    p.loadProcesso(filenames[i]);
                    Entry e = new Entry(p.getPName(), p.getPID(), p.averageMEM(begin, end), p.averageMEM(begin, end));
                    if (entries_aux[i] != null && entries_aux[indexOfLesserCPU(entries_aux)].getMEM() <= e.getMEM()) {
                        entries_aux[indexOfLesserCPU(entries_aux)] = new Entry(e);
                    }
                    else if(entries_aux[i] == null){
                        entries_aux[i] = new Entry(e);
                    }
                }
                entries = new ArrayList<>(Arrays.asList(entries_aux));
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
    }

   public void oldest(){

   }

}
