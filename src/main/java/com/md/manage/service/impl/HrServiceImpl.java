package com.md.manage.service.impl;

import com.md.manage.domain.Hr;
import com.md.manage.dto.Page;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.HrException;
import com.md.manage.mapper.HrMapper;
import com.md.manage.model.HrModel;
import com.md.manage.model.PageModel;
import com.md.manage.service.HrService;
import com.md.manage.util.CommonUtils;
import com.md.manage.validate.IDCollection;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrServiceImpl implements HrService {


    @Autowired
    private HrMapper hrMapper;


    /**
     * 根据主键ID查找管理员账户
     * @param id
     * @return
     */
    @Override
    public Hr getHrById(String id) throws BaseException{
        Validate validate = new IdMustBePositiveInt(id);
        boolean r  = validate.goCheck();
        if(!r){
            throw new BaseException("参数错误",404,10001);
        }
        Hr hr = hrMapper.getHrById(Integer.parseInt(id));
        if(hr == null){
            throw new HrException("管理员不存在",401,20001);
        }
        return hr;
    }

    /**
     * 根据用户名查找管理员用户
     * @param username
     * @return
     */
    @Override
    public Hr getHrByUsername(String username) {
        return hrMapper.getHrByUsername(username);
    }

    /**
     * 增加管理员账户
     * @param hrModel
     * @return
     */
    public int insertHr(HrModel hrModel) throws HrException{
        Hr eHr = hrMapper.getHrByUsername(hrModel.getUsername());
        if(eHr!=null){
            throw new HrException("用户名重复了!");
        }
        Hr hr = new Hr();
        BeanUtils.copyProperties(hrModel, hr);
        hr.setCreateTime(CommonUtils.getCurrentDate());
        hr.setUpdateTime(CommonUtils.getCurrentDate());
        hr.setStatus(hrModel.getStatus());
        int effectNum =  hrMapper.insert(hr);
        if(effectNum==0){
            throw new HrException("操作失败",401,20001);
        }
        return effectNum;
    }

    /**
     * 修改管理员账户信息
     * @param hrModel
     * @return
     */
    public int updateHr(HrModel hrModel)throws HrException,BaseException{
        if(hrModel.getId()==null){
            throw new BaseException("必须传入ID");
        }else {
            Validate validate =new IdMustBePositiveInt(hrModel.getId());
            boolean r =validate.goCheck();
            if(!r){
                throw new BaseException("Id必须为正整数",404,10001);
            }
        }
        Hr hr = new Hr();
        BeanUtils.copyProperties(hrModel, hr);
        hr.setId(Integer.parseInt(hrModel.getId()));
        hr.setUpdateTime(CommonUtils.getCurrentDate());
        int effectNum =  hrMapper.update(hr);
        if(effectNum==0){
            throw new HrException("操作失败",401,20002);
        }
        return effectNum;
    }

    @Override
    public int deleteHr(String id) throws HrException,BaseException{
        Validate validate =new IdMustBePositiveInt(id);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("Id必须为正整数",404,10001);
        }
        int effectNum =  hrMapper.delete(Integer.parseInt(id));
        if(effectNum==0){
            throw new HrException("操作失败",401,20002);
        }
        hrMapper.deleteHrRole(Integer.parseInt(id));
        return effectNum;
    }

    @Override
    public List<Hr> findAll() {
        return hrMapper.findAll();
    }


    @Override
    public Page<Hr> getHrListByPage(PageModel pageModel) {
        Long count = hrMapper.count();
        Page<Hr> pageInfo = new Page(pageModel.getPageNum(),pageModel.getPageSize(),count);
        pageInfo.pagination();
        List<Hr> hrs = hrMapper.getListByPage(pageInfo);
        pageInfo.setList(hrs);
        return pageInfo;
    }


    /**
     * 分配角色
     * @param uid
     * @param strIds
     * @return
     */
    @Override
    public int setRoles(String uid, String strIds) {
        Validate validate = new IdMustBePositiveInt(uid);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("id必须为正整数",404,10001);
        }
        validate = new IDCollection(strIds);
        r = validate.goCheck();
        if(!r){
            throw new BaseException("ids必须为多个逗号分隔的正整数集",404,10001);
        }
        hrMapper.deleteHrRole(Integer.parseInt(uid));
        List<Integer> ids = CommonUtils.StringToListOfInteger(strIds);
        int effect = hrMapper.setRoles(Integer.parseInt(uid),ids);
        if(effect==0){
            throw new HrException("操作失败",401,20002);
        }
        return effect;
    }
}
