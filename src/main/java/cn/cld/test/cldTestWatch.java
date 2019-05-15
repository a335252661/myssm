package cn.cld.test;

import java.nio.file.*;

public class cldTestWatch {
    public static void main(String[] args) {
        try{

//创建一个监听服务
            WatchService service=FileSystems.getDefault().newWatchService();
//设置路径
            Path path=Paths.get("C:\\tmp");
//注册监听器
            path.register(service, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey watchKey;

//使用dowhile
            do{
//获取一个watch key
                watchKey=service.take();
                for(WatchEvent<?> event:watchKey.pollEvents()){
//如果时间列表不为空，打印事件内容
                    WatchEvent.Kind<?> kind=event.kind();
                    Path eventPath=(Path)event.context();
                    System.out.println(eventPath+":"+kind+":"+eventPath);

                }
                System.out.println("目录内容发生改变");

            }while(watchKey.reset());
        }catch(Exception e){
            e.printStackTrace();

        }

// 1、通过FileSystems.getDefault().newWatchService()创建一个监听服务；
// 2、设置路径；
// 3、对目录注册一个监听器；
// 4、之后进入循环，等待watch key；
// 5、此时如果有事件发生可通过watchkey的pollevent()方法获取；
// 6、之后可以对event处理；
    }
}
