package com.md.manage.controller;


import com.md.manage.domain.Hr;
import com.md.manage.dto.Page;
import com.md.manage.exception.BaseException;
import com.md.manage.json.JsonResult;
import com.md.manage.model.HrModel;
import com.md.manage.model.PageModel;
import com.md.manage.service.HrService;
import com.md.manage.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HrController {


    @Autowired
    private HrService hrService;


    @GetMapping("/api/hr/{id}")
    public JsonResult getHrById(@PathVariable("id") String id){
        Hr hr= hrService.getHrById(id);
        return new JsonResult().success(hr);
    }

    /**
     * 添加管理员账户
     * @param hrModel
     * @param result
     * @return
     * @throws BaseException
     */
    @PostMapping("/api/hr/add")
    public JsonResult insertHr(@RequestBody @Valid HrModel hrModel, BindingResult result) throws BaseException{
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }        }
        int effect = hrService.insertHr(hrModel);
        return new JsonResult().success(effect);
    }

    /**
     * 修改管理员账户
     * @param hrModel
     * @param result
     * @return
     * @throws BaseException
     */
    @PostMapping("/api/hr/update")
    public JsonResult updateHr(@RequestBody @Valid HrModel hrModel, BindingResult result) throws BaseException{
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
        return new JsonResult().success(hrService.updateHr(hrModel));
    }

    /**
     * 删除管理员账户
     * @param id
     * @return
     */
    @GetMapping("/api/hr/{id}/delete")
    public JsonResult deleteHr(@PathVariable("id") String id){
        return new JsonResult().success(hrService.deleteHr(id));
    }


    /**
     * 得到分页的管理员列表
     */
    @GetMapping("/api/hr/list")
    public JsonResult getHrList(@Valid PageModel pageModel, BindingResult result){
        CommonUtils.validateParams(result);
        Page<Hr> pageInfo = hrService.getHrListByPage(pageModel);
        return new JsonResult().success(pageInfo);
    }

}
