package com.md.manage.service;

import com.md.manage.domain.LaboratorySheet;
import com.md.manage.dto.Page;
import com.md.manage.model.PageModel;

import java.util.List;

public interface LaboratorySheetService {


    Page<LaboratorySheet> getLaboratorySheetList(PageModel pageModel,String token);

    List<LaboratorySheet> findAll();

    int setSuggest(String suggest,String lsId);

    int insertByDoctorId(String patientId, String token);

}
