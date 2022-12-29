package utils;

import global.Global;

import java.io.*;
import java.util.ArrayList;

/** ListMap
 *      һ��ʵ�������Ӧһ���ļ������ڱ������д�������
 *
 *  һ��ListMap�������ListGroupInfo
 *  һ��ListGroupInfo�������ListItemInfo
 * */
public class ListMap implements Serializable{
    private ArrayList<ListGroupInfo> listMap;
    private String path;

    public ListMap(String path) {
        this.path = path;
        File file = new File(path);
        if(file.exists()){
            // �ļ����ڣ�ֱ�Ӷ���
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
            // �ļ������ڣ���ʼ��
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
        // ���Բ���
        for (ListGroupInfo listGroup : listMap) {
            if (listGroup.getYear() == y
                    && listGroup.getMonth() == m
                    && listGroup.getDate() == d) {
                target = listGroup;
                break;
            }
        }
        // �����ѯ������Ϊnull�����½�����ʼ������
        if(target==null){
            target = new ListGroupInfo();
            target.setDate(y,m,d);
            this.listMap.add(target);
            this.saveToStorage();
            System.out.println("init ListGroupInfo");
        }
        // ���
        target.showList();
        return target;
    }

    public void addListGroup(ListGroupInfo listGroup){
        this.listMap.add(listGroup);
        this.saveToStorage();
    }
}
