package com.md.manage.model;

import org.hibernate.validator.constraints.NotBlank;

public class SpecificItemModel {

    private String id;

    @NotBlank
    private String result;

    @NotBlank
    private String specificId;


    @NotBlank
    private String lsId;

    @NotBlank
    private String checker;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSpecificId() {
        return specificId;
    }

    public void setSpecificId(String specificId) {
        this.specificId = specificId;
    }


    public String getLsId() {
        return lsId;
    }

    public void setLsId(String lsId) {
        this.lsId = lsId;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }
}
