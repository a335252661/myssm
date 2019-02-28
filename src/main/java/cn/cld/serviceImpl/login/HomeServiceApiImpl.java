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


    @Override
    public List getJsonMenu2() {
        ModuleExample me1 = new ModuleExample();
        me1.createCriteria().andLevelEqualTo(1);
        List<Module> modulesLevel1 = moduleMapper.selectByExample(me1);

        //用于存所有的一级map
        List<Map> list1 =new ArrayList<Map>();

        List<Map> maps = menuAdd(modulesLevel1, list1);

        System.out.println(maps);
        return maps;
    }

    private List<Map> menuAdd(List<Module> moduleList,List<Map> list1) {

        for(Module vo1:moduleList){
            Map map1 = new HashMap();
            if(1==vo1.getIsFile()){
                Map attributesMap = new HashMap();
                attributesMap.put("url",vo1.getUrl());

                map1.put("text",vo1.getModuleName());
                map1.put("attributes",attributesMap);

                //将map数据放入数组
                list1.add(map1);
            }else {
                map1.put("text",vo1.getModuleName());
                map1.put("state",vo1.getState());


                //有二级目录
                ModuleExample me = new ModuleExample();
                me.createCriteria().andPardentNoEqualTo(vo1.getModuleNo());
                List<Module> modulesLevel2 = moduleMapper.selectByExample(me);
                List<Map> childrenList =new ArrayList<Map>();
                List<Map> maps = menuAdd(modulesLevel2, childrenList);

                map1.put("children",maps);

                list1.add(map1);
            }
        }
    return list1;
    }

}
