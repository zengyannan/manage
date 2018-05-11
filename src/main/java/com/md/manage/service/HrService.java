package com.md.manage.service;


import com.md.manage.domain.Hr;
import com.md.manage.dto.Page;
import com.md.manage.model.HrModel;
import com.md.manage.model.PageModel;

import java.util.List;

public interface HrService {




    public Hr getHrById(String id) ;

    public Hr getHrByUsername(String username);

    public int insertHr(HrModel hrModel);

    public int updateHr(HrModel hrModel);


    public int deleteHr(String id);

    public List<Hr> findAll();


    Page<Hr> getHrListByPage(PageModel page);

    List<Hr> getListByRoleName(String roleName);

    int setRoles(String uid, String ids);
}
