package cn.cld.pojo;

public class Module {
    private String moduleNo;

    private Integer level;

    private String moduleName;

    private String pardentNo;

    private String pardentName;

    private Integer isFile;

    private String url;

    private String iconCls;

    private String state;

    public String getModuleNo() {
        return moduleNo;
    }

    public void setModuleNo(String moduleNo) {
        this.moduleNo = moduleNo == null ? null : moduleNo.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getPardentNo() {
        return pardentNo;
    }

    public void setPardentNo(String pardentNo) {
        this.pardentNo = pardentNo == null ? null : pardentNo.trim();
    }

    public String getPardentName() {
        return pardentName;
    }

    public void setPardentName(String pardentName) {
        this.pardentName = pardentName == null ? null : pardentName.trim();
    }

    public Integer getIsFile() {
        return isFile;
    }

    public void setIsFile(Integer isFile) {
        this.isFile = isFile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls == null ? null : iconCls.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}