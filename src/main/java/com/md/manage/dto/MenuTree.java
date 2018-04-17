package com.md.manage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.md.manage.domain.Menu;

import java.util.List;

public class MenuTree {

    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private Integer parentId;

    private boolean leaf;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuTree> children;

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
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
