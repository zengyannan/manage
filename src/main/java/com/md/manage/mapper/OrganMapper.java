package com.md.manage.mapper;

import com.md.manage.domain.Organ;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrganMapper {


    List<Organ> findAll();

    int  insert(Organ role);

    int update(Organ role);

    int delete(@Param("id") Integer id);

}
