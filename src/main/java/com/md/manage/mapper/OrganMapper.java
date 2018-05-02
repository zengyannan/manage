package com.md.manage.mapper;

import com.md.manage.domain.Organ;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrganMapper {


    List<Organ> findAll();

    int  insert(Organ organ);

    int update(Organ organ);

    int delete(@Param("id") Integer id);

    List<Organ> getAllOrgan();

}
