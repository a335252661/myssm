package cn.cld.test.watch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by cld
 * @date 2019/5/13 13:38
 * @description: 被观察者
 */
public class DataManager {

    //list中存放的是接口，接口调用方法，则所有子类就会调用重写的方法
    private List<IDataListenApi> listenList = new ArrayList<>();

    public void notifyListen(Object event,Object msg){
        for(IDataListenApi dataListen : listenList){
            dataListen.update(null, null);
        }
    }

    public void addListen(IDataListenApi dataListen){
        listenList.add(dataListen);
    }

    public void updateData(Object msg){
        this.notifyListen(null, msg);
    }


    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        IDataListenApi dataListen1 = new DataListenImpl();
        IDataListenApi dataListen2 = new DataListenImpl();

        dataManager.addListen(dataListen1);
        dataManager.addListen(dataListen2);
        dataManager.updateData("aaa");
    }
}
