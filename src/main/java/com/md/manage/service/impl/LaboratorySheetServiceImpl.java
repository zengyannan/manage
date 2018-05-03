package com.md.manage.service.impl;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.md.manage.domain.Hr;
import com.md.manage.domain.LaboratorySheet;
import com.md.manage.dto.Page;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.LaboratorySheetException;
import com.md.manage.mapper.LaboratorySheetMapper;
import com.md.manage.model.PageModel;
import com.md.manage.service.LaboratorySheetService;
import com.md.manage.service.RedisService;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LaboratorySheetServiceImpl implements LaboratorySheetService {


    @Autowired
    private LaboratorySheetMapper laboratorySheetMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public Page<LaboratorySheet> getLaboratorySheetList(PageModel pageModel,String token) {
        Hr doctor = (Hr)redisService.get("token:"+token);
        Long count = laboratorySheetMapper.countByDoctorId(doctor.getId());
        Page<LaboratorySheet> pageInfo = new Page(pageModel.getPageNum(),pageModel.getPageSize(),count);
        pageInfo.pagination();
        List<LaboratorySheet> laboratorySheetList = laboratorySheetMapper.getListByPage(pageInfo,doctor.getId());
        pageInfo.setList(laboratorySheetList);
        return  pageInfo;
    }


    @Override
    public List<LaboratorySheet> findAll() {
        return laboratorySheetMapper.findAll();
    }


    @Override
    public int setSuggest(String suggest, String lsId) {
        Validate validate = new IdMustBePositiveInt(lsId);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("id必须为正整数",404,10001);
        }
        if(suggest==null||suggest.isEmpty()){
            throw new BaseException("医生建议不能为空",404,10001);

        }
        int effect = laboratorySheetMapper.setSuggest(suggest,Integer.parseInt(lsId),2);
        if(effect==0){
            throw new LaboratorySheetException("操作错误");
        }
        return effect;
    }
}
