package cn.cld.serviceImpl.login;

import cn.cld.dao.ModuleMapper;
import cn.cld.dao.login.MyModuleMapper;

import cn.cld.pojo.Module;
import cn.cld.pojo.ModuleExample;
import cn.cld.service.login.HomeServiceApi;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeServiceApiImpl implements HomeServiceApi {
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private MyModuleMapper myModuleMapper;

    public List getJsonMenu() {
                ModuleExample me3 = new ModuleExample();
        me3.createCriteria().andLevelEqualTo(3);
        List<Module> modulesLevel3 = moduleMapper.selectByExample(me3);

        ModuleExample me2 = new ModuleExample();
        me2.createCriteria().andLevelEqualTo(2);
        List<Module> modulesLevel2 = moduleMapper.selectByExample(me2);

        ModuleExample me1 = new ModuleExample();
        me1.createCriteria().andLevelEqualTo(1);
        List<Module> modulesLevel1 = moduleMapper.selectByExample(me1);



        List<Map> list1 =new ArrayList<Map>();

        for (Module vo1:modulesLevel1){
            Map map1 = new HashMap();
            checkFile(vo1,map1);


            for(Module vo2:modulesLevel2){
                if (vo2.getPardentNo().equals(vo1.getModuleNo())){
                     Map map2 = new HashMap();
                    List<Map> list2 =new ArrayList<Map>();
                     //list2.clear();

                    checkFile(vo2,map2);

                //Map map
                    for(Module vo3:modulesLevel3){
                        if(vo3.getPardentNo().equals(vo2.getModuleNo())){

                            //Map mapchildren = new HashMap();
                            Map map3 = new HashMap();
                            List<Map> list3 =new ArrayList<Map>();
                            checkFile(vo3,map3);

                            list3.add(map3);
                            map2.put("children",list3);
                            vo2.getState();
                            if ("closed".equals(vo2.getState())){
                                map2.put("state","closed");
                            }
                            map2.put("text",vo2.getModuleName());
                        }
                    }
                    list2.add(map2);
                    map1.put("text",vo1.getModuleName());
                    if ("closed".equals(vo1.getState())){
                        map1.put("state","closed");
                    }
                    map1.put("children",list2);
            }
            }

            list1.add(map1);
        }

        return list1;
    }
    public int checkFile(Module vo1 , Map map1){
        if(1==vo1.getIsFile()){
            Map map1url = new HashMap();
            map1url.put("url",vo1.getUrl());
            map1.put("text",vo1.getModuleName());
            map1.put("attributes",map1url);
        }
        return vo1.getIsFile();
    }
}
