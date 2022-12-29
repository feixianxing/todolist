package utils;

import global.Global;

import java.io.*;
import java.util.ArrayList;

/** ListMap
 *      一个实例对象对应一个文件，用于保存所有待办事项
 *
 *  一个ListMap包含多个ListGroupInfo
 *  一个ListGroupInfo包含多个ListItemInfo
 * */
public class ListMap implements Serializable{
    private ArrayList<ListGroupInfo> listMap;
    private String path;

    public ListMap(String path) {
        this.path = path;
        File file = new File(path);
        if(file.exists()){
            // 文件存在，直接读入
            try {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream input = new ObjectInputStream(fileIn);
                listMap = (ArrayList<ListGroupInfo>) input.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            // 文件不存在，初始化
            try {
                file.createNewFile();
                listMap = new ArrayList<>();
                saveToStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveToStorage(){
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream output = new ObjectOutputStream(fileOut);
            output.writeObject(listMap);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ListGroupInfo getListGroup(int y, int m, int d){
        ListGroupInfo target = null;
        // 线性查找
        for (ListGroupInfo listGroup : listMap) {
            if (listGroup.getYear() == y
                    && listGroup.getMonth() == m
                    && listGroup.getDate() == d) {
                target = listGroup;
                break;
            }
        }
        // 如果查询的日期为null，则新建并初始化日期
        if(target==null){
            target = new ListGroupInfo();
            target.setDate(y,m,d);
            this.listMap.add(target);
            this.saveToStorage();
            System.out.println("init ListGroupInfo");
        }
        // 输出
        target.showList();
        return target;
    }

    public void addListGroup(ListGroupInfo listGroup){
        this.listMap.add(listGroup);
        this.saveToStorage();
    }
}
