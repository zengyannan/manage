package com.md.manage.mapper;

import com.md.manage.domain.Inspection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InspectionMapper {



    List<Inspection> findAll();


    int  insert(Inspection inspection);

    int update(Inspection inspection);

    int delete(@Param("id") Integer id);

}
