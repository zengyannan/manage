package com.md.manage.service;

import com.md.manage.domain.Organ;
import com.md.manage.model.OrganModel;

import java.util.List;

public interface OrganService {


    List<Organ> getOrganList();

    List<Organ> getAllOrgan();

    int insertOrgan(OrganModel organModel);

    int updateOrgan(OrganModel organModel);

    int deleteOrgan(String id);

    Organ getOrganById(String id);


}
