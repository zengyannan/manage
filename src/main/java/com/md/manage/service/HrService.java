package com.md.manage.service;

import com.md.manage.domain.Hr;
import com.md.manage.model.HrModel;

public interface HrService {




    public Hr getHrById(String id) ;

    public Hr getHrByUsername(String username);

    public int insertHr(HrModel hrModel);

    public int updateHr(HrModel hrModel);


    public int deleteHr(String id);


}
