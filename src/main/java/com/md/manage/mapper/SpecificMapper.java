package com.md.manage.mapper;

import com.md.manage.domain.Specific;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecificMapper {


    List<Specific> findAll();

    Specific findById(@Param("id") Integer id);

    int insert(Specific specific);

    int update(Specific specific);

    int delete(@Param("id") Integer id);
}
