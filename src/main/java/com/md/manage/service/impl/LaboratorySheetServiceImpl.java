package com.md.manage.service.impl;


import com.md.manage.domain.LaboratorySheet;
import com.md.manage.domain.SpecificItem;
import com.md.manage.domain.SystemSuggest;
import com.md.manage.dto.Page;
import com.md.manage.dto.User;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.LaboratorySheetException;
import com.md.manage.mapper.LaboratorySheetMapper;
import com.md.manage.mapper.SpecificItemMapper;
import com.md.manage.mapper.SystemSuggestMapper;
import com.md.manage.model.PageModel;
import com.md.manage.service.LaboratorySheetService;
import com.md.manage.service.RedisService;
import com.md.manage.util.CommonUtils;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorySheetServiceImpl implements LaboratorySheetService {


    @Autowired
    private LaboratorySheetMapper laboratorySheetMapper;

    @Autowired
    private SpecificItemMapper specificItemMapper;

    @Autowired
    private SystemSuggestMapper systemSuggestMapper;


    @Autowired
    private RedisService redisService;

    @Override
    public Page<LaboratorySheet> getLaboratorySheetList(PageModel pageModel,String token) {
        User user = (User) redisService.get("token:"+token);
        List<LaboratorySheet> laboratorySheetList=null;
        Page<LaboratorySheet> pageInfo=null;
        if(user.getLoginType().equals("hr")){
            Long count = laboratorySheetMapper.countByDoctorId(user.getId());
            pageInfo = new Page(pageModel.getPageNum(),pageModel.getPageSize(),count);
            pageInfo.pagination();
            laboratorySheetList = laboratorySheetMapper.getListByPageAndDoctor(pageInfo,user.getId(),null);
        }else if(user.getLoginType().equals("patient")){
            Long count = laboratorySheetMapper.countByPatientId(user.getId());
            pageInfo = new Page(pageModel.getPageNum(),pageModel.getPageSize(),count);
            pageInfo.pagination();
            laboratorySheetList = laboratorySheetMapper.getListByPageAndPatient(pageInfo,user.getId(),null);
        }

        for(LaboratorySheet laboratorySheet:laboratorySheetList){
            List<SpecificItem> specificItemList = specificItemMapper.getListByLsId(laboratorySheet.getId());
            List<SystemSuggest> systemSuggests =systemSuggestMapper.findAll();
            StringBuilder suggest = new StringBuilder("");
            for(SpecificItem specificItem :specificItemList){
                for (SystemSuggest systemSuggest :systemSuggests){
                    if(specificItem.getSpecificId()==systemSuggest.getSpecificId()
                            &&specificItem.getTips()==systemSuggest.getTips()){
                        suggest.append(systemSuggest.getSuggest());
                    }
                }
            }
            laboratorySheet.setSystemSuggest(suggest.toString());
        }
        pageInfo.setList(laboratorySheetList);
        return  pageInfo;
    }

    @Override
    public Page<LaboratorySheet> getLaboratorySheetListByOrganId(PageModel pageModel,String organId,String token) {
        Validate validate = new IdMustBePositiveInt(organId);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("organId必须为正整数");
        }
        User user = (User) redisService.get("token:"+token);
        List<LaboratorySheet> laboratorySheetList=null;
        Page<LaboratorySheet> pageInfo=null;
        if(user.getLoginType().equals("hr")){
            Long count = laboratorySheetMapper.countByDoctorId(user.getId());
            pageInfo = new Page(pageModel.getPageNum(),pageModel.getPageSize(),count);
            pageInfo.pagination();
            laboratorySheetList = laboratorySheetMapper.getListByPageAndDoctor(pageInfo,user.getId(),Integer.parseInt(organId));
        }else if(user.getLoginType().equals("patient")){
            Long count = laboratorySheetMapper.countByPatientId(user.getId());
            pageInfo = new Page(pageModel.getPageNum(),pageModel.getPageSize(),count);
            pageInfo.pagination();
            laboratorySheetList = laboratorySheetMapper.getListByPageAndPatient(pageInfo,user.getId(),Integer.parseInt(organId));
        }

        for(LaboratorySheet laboratorySheet:laboratorySheetList){
            List<SpecificItem> specificItemList = specificItemMapper.getListByLsId(laboratorySheet.getId());
            List<SystemSuggest> systemSuggests =systemSuggestMapper.findAll();
            StringBuilder suggest = new StringBuilder("");
            for(SpecificItem specificItem :specificItemList){
                for (SystemSuggest systemSuggest :systemSuggests){
                    if(specificItem.getSpecificId()==systemSuggest.getSpecificId()
                            &&specificItem.getTips()==systemSuggest.getTips()){
                        suggest.append(systemSuggest.getSuggest());
                    }
                }
            }
            laboratorySheet.setSystemSuggest(suggest.toString());
        }
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

    @Override
    public int insertByDoctorId(String patientId, String organId,String token) {
        Validate validate = new IdMustBePositiveInt(patientId);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("patientId必须为正整数",404,10001);
        }
        validate = new IdMustBePositiveInt(organId);
        r = validate.goCheck();
        if(!r){
            throw new BaseException("organId必须为正整数",404,10001);
        }
        User doctor = (User) redisService.get("token:"+token);
        LaboratorySheet laboratorySheet =new LaboratorySheet();
        laboratorySheet.setDoctorId(doctor.getId());
        laboratorySheet.setPatientId(Integer.parseInt(patientId));
        laboratorySheet.setOrganId(Integer.parseInt(organId));
        laboratorySheet.setStatus(1);
        laboratorySheet.setSuggest("");
        laboratorySheet.setSystemSuggest("");
        laboratorySheet.setCreateTime(CommonUtils.getCurrentDate());
        laboratorySheet.setUpdateTime(CommonUtils.getCurrentDate());
        int effect=laboratorySheetMapper.insert(laboratorySheet);
        if(effect==0){
            throw new LaboratorySheetException("操作错误");
        }
        return effect;
    }

    @Override
    public int insertByPatientId(String doctorId, String organId, String token) {
        Validate validate = new IdMustBePositiveInt(doctorId);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("doctorId必须为正整数",404,10001);
        }
        validate = new IdMustBePositiveInt(organId);
        r = validate.goCheck();
        if(!r){
            throw new BaseException("organId必须为正整数",404,10001);
        }
        User patient = (User) redisService.get("token:"+token);
        LaboratorySheet laboratorySheet =new LaboratorySheet();
        laboratorySheet.setDoctorId(Integer.parseInt(doctorId));
        laboratorySheet.setPatientId(patient.getId());
        laboratorySheet.setOrganId(Integer.parseInt(organId));
        laboratorySheet.setStatus(1);
        laboratorySheet.setSuggest("");
        laboratorySheet.setSystemSuggest("");
        laboratorySheet.setCreateTime(CommonUtils.getCurrentDate());
        laboratorySheet.setUpdateTime(CommonUtils.getCurrentDate());
        int effect=laboratorySheetMapper.insert(laboratorySheet);
        if(effect==0){
            throw new LaboratorySheetException("操作错误");
        }
        return effect;
    }
}
