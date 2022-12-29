package utils;

import global.Global;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

// 一个实例对象对应一天的实例对象
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
        // 数据填充 与 计数
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

    // 获取列表
    public ArrayList<ListItemInfo> getList(){
        return this.list;
    }

    // 添加待办事项
    public void addListItem(String text){
        this.list.add(
                new ListItemInfo(text,new Date(), false)
        );
        this.total++;
        this.undoneCount++;
    }
    // 根据创建日期查找对应待办事项
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

    // 完成指定待办事项
    public void setDoneByIndex(int index){
        list.get(index).setDone();
        undoneCount--;
        doneCount++;
    }

    // 删除指定待办事项
    public void setDeleteByIndex(int index){
        if(list.get(index).hasDone()){
            doneCount--;
        }else{
            undoneCount--;
        }
        total--;

        list.remove(index);
    }

    // 在控制台输出所有待办事项
    public void showList(){
        for(ListItemInfo item:list){
            System.out.println(item.getText()+" "+item.getCreateDate().getTime()+" "+item.hasDone());
        }
    }
}
