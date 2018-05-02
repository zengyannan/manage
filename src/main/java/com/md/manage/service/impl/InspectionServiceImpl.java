package com.md.manage.service.impl;

import com.md.manage.domain.Inspection;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.InspectionException;
import com.md.manage.mapper.InspectionMapper;
import com.md.manage.model.InspectionModel;
import com.md.manage.service.InspectionService;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InspectionServiceImpl implements InspectionService {


    @Autowired
    private InspectionMapper inspectionMapper;


    @Override
    public List<Inspection> findAll() {
        return inspectionMapper.findAll();
    }


    @Override
    public int insertInspection(InspectionModel inspectionModel) {
        Inspection inspection = new Inspection();
        Validate validate = new IdMustBePositiveInt(inspectionModel.getOrganId());
        boolean r= validate.goCheck();
        if(!r){
            throw new BaseException("organID必须为正整数",404,10001);
        }
        BeanUtils.copyProperties(inspectionModel,inspection);
        inspection.setOrganId(Integer.parseInt(inspectionModel.getOrganId()));
        int effectNum = inspectionMapper.insert(inspection);
        if(effectNum==0){
            throw new InspectionException("操作失败");
        }
        return effectNum;
    }

    @Override
    public int updateInspection(InspectionModel inspectionModel) {
        Validate validate;
        if(inspectionModel.getId()==null){
            throw new BaseException("必须传入ID");
        }else {
            validate = new IdMustBePositiveInt(inspectionModel.getId());
            boolean r= validate.goCheck();
            if(!r){
                throw new BaseException("ID必须为正整数",404,10001);
            }
        }
        validate = new IdMustBePositiveInt(inspectionModel.getOrganId());
        boolean r= validate.goCheck();
        if(!r){
            throw new BaseException("organID必须为正整数",404,10001);
        }
        Inspection inspection = new Inspection();
        BeanUtils.copyProperties(inspectionModel,inspection);
        inspection.setId(Integer.parseInt(inspectionModel.getId()));
        inspection.setOrganId(Integer.parseInt(inspectionModel.getOrganId()));
        int effect = inspectionMapper.update(inspection);
        if(effect==0){
            throw new InspectionException("操作失败");
        }
        return effect;
    }

    @Override
    public int deleteInspection(String id) {
        Validate validate =new IdMustBePositiveInt(id);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("参数错误",404,10001);
        }
        int effect = inspectionMapper.delete(Integer.parseInt(id));
        if(effect==0){
            throw new InspectionException("操作错误");
        }
        return effect;
    }
}
