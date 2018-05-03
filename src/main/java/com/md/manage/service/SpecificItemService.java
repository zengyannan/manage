package com.md.manage.service;

import com.md.manage.domain.SpecificItem;
import com.md.manage.model.SpecificItemModel;
import com.md.manage.model.SpecificModel;

import java.util.List;

public interface SpecificItemService {


    List<SpecificItem> findAll();

    List<SpecificItem> getListByLsId(String lsId);


    int  insertSpecificItem(SpecificItemModel specificItemModel);

    int  updateSpecificItem(SpecificItemModel specificItemModel);

    int  deleteSpecificItem(String id);


}
