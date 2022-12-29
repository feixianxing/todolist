package utils;

import java.io.Serializable;
import java.util.Date;

// һ��ʵ�������Ӧһ�������������Ϣ
public class ListItemInfo implements Serializable {
    private String listItemText;
    private Date date;
    private boolean done;

    public ListItemInfo(String text,Date date,boolean done){
        this.listItemText = text;
        this.date = date;
        this.done = done;
    }

    public void setDone(){
        this.done = true;
    }
    public boolean hasDone(){
        return this.done;
    }

    public Date getCreateDate(){
        return this.date;
    }

    public String getText(){
        return this.listItemText;
    }
}
