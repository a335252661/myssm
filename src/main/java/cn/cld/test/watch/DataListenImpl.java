package cn.cld.test.watch;

public class DataListenImpl implements IDataListenApi {
    @Override
    public void update(Object event, Object arg) {
        // TODO Auto-generated method stub
        System.out.println("数据发生了变化");
    }
}
