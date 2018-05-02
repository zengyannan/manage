package com.md.manage.service.impl;

import com.md.manage.domain.LaboratorySheet;
import com.md.manage.mapper.LaboratorySheetMapper;
import com.md.manage.service.LaboratorySheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorySheetServiceImpl implements LaboratorySheetService {


    @Autowired
    private LaboratorySheetMapper laboratorySheetMapper;

    @Override
    public List<LaboratorySheet> getLaboratorySheetList() {
        return laboratorySheetMapper.findAll();
    }

}
