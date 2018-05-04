package com.md.manage.domain;

public class SystemSuggest {


    private Integer id;

    private Integer specificId;

    private Integer tips;

    private String suggest;


    private Specific specific;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}
