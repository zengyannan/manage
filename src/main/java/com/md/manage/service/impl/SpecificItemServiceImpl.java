package com.md.manage.service.impl;

import com.md.manage.domain.Specific;
import com.md.manage.domain.SpecificItem;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.SpecificItemException;
import com.md.manage.mapper.SpecificItemMapper;
import com.md.manage.mapper.SpecificMapper;
import com.md.manage.model.SpecificItemModel;
import com.md.manage.service.SpecificItemService;
import com.md.manage.util.CommonUtils;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificItemServiceImpl implements SpecificItemService {


    @Autowired
    private SpecificItemMapper specificItemMapper;

    @Autowired
    private SpecificMapper specificMapper;

    @Override
    public List<SpecificItem> findAll() {
        return specificItemMapper.findAll();
    }

    @Override
    public int insertSpecificItem(SpecificItemModel specificItemModel) {
        Validate validate = new IdMustBePositiveInt(specificItemModel.getSpecificId());
        boolean r = validate.goCheck();
        if (!r) {
            throw new BaseException("检验指标Id必须为正整数", 404, 10001);
        }
        validate = new IdMustBePositiveInt(specificItemModel.getLsId());
        r = validate.goCheck();
        if (!r) {
            throw new BaseException("检验单id必须为正整数", 404, 10001);
        }

        SpecificItem specificItem = new SpecificItem();
        BeanUtils.copyProperties(specificItemModel, specificItem);
        specificItem.setSpecificId(Integer.parseInt(specificItemModel.getSpecificId()));
        specificItem.setLsId(Integer.parseInt(specificItemModel.getLsId()));
        Specific specific = specificMapper.findById(specificItem.getSpecificId());
        specificItem.setTips(CommonUtils.computeTips(specificItem.getResult(),specific.getRef()));
        int effectNum = specificItemMapper.insert(specificItem);
        if (effectNum == 0) {
            throw new SpecificItemException("操作失败");
        }
        return effectNum;
    }

    @Override
    public int updateSpecificItem(SpecificItemModel specificItemModel) {
        Validate validate = new IdMustBePositiveInt(specificItemModel.getId());
        boolean r = validate.goCheck();
        if (!r) {
            throw new BaseException("id必须为正整数", 404, 10001);
        }
        validate = new IdMustBePositiveInt(specificItemModel.getSpecificId());
        r = validate.goCheck();
        if (!r) {
            throw new BaseException("检验指标Id必须为正整数", 404, 10001);
        }
        validate = new IdMustBePositiveInt(specificItemModel.getLsId());
        r = validate.goCheck();
        if (!r) {
            throw new BaseException("检验单id必须为正整数", 404, 10001);
        }
        SpecificItem specificItem = new SpecificItem();
        BeanUtils.copyProperties(specificItemModel, specificItem);
        specificItem.setId(Integer.parseInt(specificItemModel.getId()));
        specificItem.setSpecificId(Integer.parseInt(specificItemModel.getSpecificId()));
        specificItem.setLsId(Integer.parseInt(specificItemModel.getLsId()));
        Specific specific = specificMapper.findById(specificItem.getSpecificId());
        specificItem.setTips(CommonUtils.computeTips(specificItem.getResult(),specific.getRef()));
        int effectNum = specificItemMapper.update(specificItem);
        if (effectNum == 0) {
            throw new SpecificItemException("操作失败");
        }
        return effectNum;
    }

    @Override
    public int deleteSpecificItem(String id) {
        Validate validate =new IdMustBePositiveInt(id);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("参数错误",404,10001);
        }
        int effect = specificItemMapper.delete(Integer.parseInt(id));
        if(effect==0){
            throw new SpecificItemException("操作错误");
        }
        return effect;
    }
}
