package com.md.manage.validate;

public class IdMustBePositiveInt extends Validate {

    private String id;

    public IdMustBePositiveInt(String id) {
        this.id=id;
    }

    @Override
    public boolean goCheck() {
        return this.isPositiveInt(id);
    }


}
