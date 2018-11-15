package cn.cld.dao.login;

import org.codehaus.jackson.map.Module;

import java.util.List;

public interface MyModuleMapper {
    int selectBigLevel();

    List<Module> selectAll();

    List<Module> select1();

    List<Module> select2();

    List<Module> select3();
}
