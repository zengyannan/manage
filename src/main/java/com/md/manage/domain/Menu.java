package com.md.manage.domain;

/**
 * 左侧菜单实体类
 */
public class Menu {
    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private Integer parentId;

    private int status;

    private String orderPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOrderPath() {
        return orderPath;
    }

    public void setOrderPath(String orderPath) {
        this.orderPath = orderPath;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", status=" + status +
                ", orderPath='" + orderPath + '\'' +
                '}';
    }

    /**
     * 如果对象类型是User 的话 则返回true 去比较hashCode值
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(obj instanceof Menu){
            Menu menu =(Menu)obj;
//          if(menu.getId() == this.id) return true; // 只比较id
            // 比较id和username 一致时才返回true 之后再去比较 hashCode
            if(menu.id == this.id && menu.getName().equals(this.name))
                return true;
        }
        return false;
    }
    public int hashCode() {
//      return id.hashCode(); // 只比较id，id一样就不添加进集合
        return id.hashCode() * name.hashCode();
    }
}