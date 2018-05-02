package com.md.manage.domain;

public class SpecificItem {

    private Integer id;

    private String result;

    private Integer specificId;

    private Integer tips;

    private Integer lsId;

    private String checker;

    private Specific specific;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getSpecificId() {
        return specificId;
    }

    public void setSpecificId(Integer specificId) {
        this.specificId = specificId;
    }

    public Integer getTips() {
        return tips;
    }

    public void setTips(Integer tips) {
        this.tips = tips;
    }

    public Integer getLsId() {
        return lsId;
    }

    public void setLsId(Integer lsId) {
        this.lsId = lsId;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public Specific getSpecific() {
        return specific;
    }

    public void setSpecific(Specific specific) {
        this.specific = specific;
    }
}
