package com.md.manage.service;

import com.md.manage.domain.Specific;
import com.md.manage.model.SpecificModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecificService {


    List<Specific> findAll();


    int  insertSpecific(SpecificModel specificModel);

    int updateSpecific(SpecificModel specificModel);

    int deleteSpecific(String id);

}
