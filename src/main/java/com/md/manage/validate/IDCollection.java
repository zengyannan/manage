package com.md.manage.validate;

public class IDCollection extends  Validate{


    String ids;


    public IDCollection(String ids) {
        this.ids = ids;
    }

    @Override
    public boolean goCheck() {
        return this.checkIDs(ids);
    }


    protected boolean checkIDs(String ids){
        if(ids==null){
            return false;
        }
        if(ids.equals("")){
            return true;
        }else {
            String[] idArr=ids.split(",");
            for(String id:idArr){
                if(!this.isPositiveInt(id))
                    return false;
            }
        }
        return true;
    }
}
