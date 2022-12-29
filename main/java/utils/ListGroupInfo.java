package utils;

import global.Global;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

// һ��ʵ�������Ӧһ���ʵ������
public class ListGroupInfo implements Serializable {
    private ArrayList<ListItemInfo> list;

    private int total;
    private int doneCount;
    private int undoneCount;
    private int year;
    private int month;
    private int date;

    public ListGroupInfo(ListItemInfo...itemArray){
        list = new ArrayList<>();
        // ������� �� ����
        total = itemArray.length;
        for(ListItemInfo item:itemArray){
            list.add(item);
            if(item.hasDone()){
                doneCount++;
            }else{
                undoneCount++;
            }
        }
    }

    public void setDate(int y,int m, int d){
        this.year = y;
        this.month = m;
        this.date = d;
    }
    public int getYear(){return this.year;}
    public int getMonth(){return this.month;}
    public int getDate(){return this.date;}

    // ��ȡ�б�
    public ArrayList<ListItemInfo> getList(){
        return this.list;
    }

    // ��Ӵ�������
    public void addListItem(String text){
        this.list.add(
                new ListItemInfo(text,new Date(), false)
        );
        this.total++;
        this.undoneCount++;
    }
    // ���ݴ������ڲ��Ҷ�Ӧ��������
    public int getListItemIndex(String timeId){
        int index = -1;
        for(int i=0;i<list.size();i++){
            if((list.get(i).getCreateDate().getTime()+"").equals(timeId)){
                index = i;
                break;
            }
        }
        return index;
    }

    // ���ָ����������
    public void setDoneByIndex(int index){
        list.get(index).setDone();
        undoneCount--;
        doneCount++;
    }

    // ɾ��ָ����������
    public void setDeleteByIndex(int index){
        if(list.get(index).hasDone()){
            doneCount--;
        }else{
            undoneCount--;
        }
        total--;

        list.remove(index);
    }

    // �ڿ���̨������д�������
    public void showList(){
        for(ListItemInfo item:list){
            System.out.println(item.getText()+" "+item.getCreateDate().getTime()+" "+item.hasDone());
        }
    }
}
