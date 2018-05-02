package com.md.manage.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class SpecificModel {

    private String id;

    private String name;

    @NotBlank
    private String zhName;

    @NotBlank
    private String inspectionId;


    private String unit;

    @NotBlank
    private String ref;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
