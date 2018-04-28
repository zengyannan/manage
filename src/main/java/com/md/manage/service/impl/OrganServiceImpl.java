package com.md.manage.service.impl;

import com.md.manage.domain.Organ;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.OrganException;
import com.md.manage.exception.RoleException;
import com.md.manage.mapper.OrganMapper;
import com.md.manage.model.OrganModel;
import com.md.manage.service.OrganService;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganServiceImpl implements OrganService {


    @Autowired
    private OrganMapper organMapper;

    @Override
    public List<Organ> getOrganList() {
        return organMapper.findAll();
    }


    @Override
    public int insertOrgan(OrganModel organModel) {
        Organ organ = new Organ();
        BeanUtils.copyProperties(organModel,organ);
        int effectNum = organMapper.insert(organ);
        if(effectNum==0){
            throw new RoleException("操作失败");
        }
        return effectNum;
    }

    @Override
    public int updateOrgan(OrganModel organModel) {
        if(organModel.getId()==null){
            throw new BaseException("必须传入ID");
        }else {
            Validate validate = new IdMustBePositiveInt(organModel.getId());
            boolean r= validate.goCheck();
            if(!r){
                throw new BaseException("ID必须为正整数",404,10001);
            }
        }
        Organ organ = new Organ();
        BeanUtils.copyProperties(organModel,organ);
        organ.setId(Integer.parseInt(organModel.getId()));
        int effect = organMapper.update(organ);
        if(effect==0){
            throw new RoleException("操作失败");
        }
        return effect;
    }

    @Override
    public int deleteOrgan(String id) {
        Validate validate =new IdMustBePositiveInt(id);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("参数错误",404,10001);
        }
        int effect = organMapper.delete(Integer.parseInt(id));
        if(effect==0){
            throw new OrganException("操作错误");
        }
        return effect;
    }
}
