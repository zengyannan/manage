package com.md.manage.service;

import com.md.manage.domain.Organ;
import com.md.manage.model.OrganModel;

import java.util.List;

public interface OrganService {


    List<Organ> getOrganList();

    int insertOrgan(OrganModel organModel);

    int updateOrgan(OrganModel organModel);

    int deleteOrgan(String id);

}
