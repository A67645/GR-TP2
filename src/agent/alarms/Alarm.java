package agent.alarms;

import java.time.LocalDateTime;

public class Alarm{
    private String type;
    private String timestamp;

    public Alarm(){
        type = "";
        timestamp = LocalDateTime.now().toString();
    }

    public Alarm(String new_type, String new_timestamp){
        type = new_type;
        timestamp = new_timestamp;
    }

    public Alarm(Alarm a){
        type = a.getType();
        timestamp = a.getTimestamp();
    }

    public String getType(){ return type; }
    public String getTimestamp(){
        return timestamp;
    }
}
