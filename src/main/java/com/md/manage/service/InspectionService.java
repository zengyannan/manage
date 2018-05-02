package com.md.manage.service;

import com.md.manage.domain.Inspection;
import com.md.manage.model.InspectionModel;

import java.util.List;


public interface InspectionService {




    public List<Inspection> findAll();

    int insertInspection(InspectionModel organModel);

    int updateInspection(InspectionModel organModel);

    int deleteInspection(String id);



}
