package com.md.manage.service.impl;

import com.md.manage.domain.Inspection;
import com.md.manage.domain.Specific;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.InspectionException;
import com.md.manage.exception.SpecificException;
import com.md.manage.mapper.SpecificMapper;
import com.md.manage.model.InspectionModel;
import com.md.manage.model.SpecificModel;
import com.md.manage.service.SpecificService;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificServiceImpl implements SpecificService {


    @Autowired
    private SpecificMapper specificMapper;

    @Override
    public List<Specific> findAll() {
        return specificMapper.findAll();
    }

    @Override
    public int insertSpecific(SpecificModel specificModel) {
        Specific specific = new Specific();
        Validate validate = new IdMustBePositiveInt(specificModel.getInspectionId());
        boolean r= validate.goCheck();
        if(!r){
            throw new BaseException("inspectionId必须为正整数",404,10001);
        }
        BeanUtils.copyProperties(specificModel,specific);
        specific.setInspectionId(Integer.parseInt(specificModel.getInspectionId()));
        int effectNum = specificMapper.insert(specific);
        if(effectNum==0){
            throw new SpecificException("操作失败");
        }
        return effectNum;
    }

    @Override
    public int updateSpecific(SpecificModel specificModel) {
        Validate validate;
        if(specificModel.getId()==null){
            throw new BaseException("必须传入ID");
        }else {
            validate = new IdMustBePositiveInt(specificModel.getId());
            boolean r= validate.goCheck();
            if(!r){
                throw new BaseException("ID必须为正整数",404,10001);
            }
        }
        validate = new IdMustBePositiveInt(specificModel.getInspectionId());
        boolean r= validate.goCheck();
        if(!r){
            throw new BaseException("inspectionId必须为正整数",404,10001);
        }
        Specific specific = new Specific();
        BeanUtils.copyProperties(specificModel,specific);
        specific.setId(Integer.parseInt(specificModel.getId()));
        specific.setInspectionId(Integer.parseInt(specificModel.getInspectionId()));
        int effect = specificMapper.update(specific);
        if(effect==0){
            throw new SpecificException("操作失败");
        }
        return effect;
    }

    @Override
    public int deleteSpecific(String id) {
        Validate validate =new IdMustBePositiveInt(id);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("参数错误",404,10001);
        }
        int effect = specificMapper.delete(Integer.parseInt(id));
        if(effect==0){
            throw new SpecificException("操作错误");
        }
        return effect;
    }
}
