package com.md.manage.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class InspectionModel {


    private String id;

    private String name;

    @NotBlank
    private String zhName;

    @NotNull
    private String organId;

    @NotBlank
    private String checkAddress;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }


    public String getCheckAddress() {
        return checkAddress;
    }

    public void setCheckAddress(String checkAddress) {
        this.checkAddress = checkAddress;
    }
}
