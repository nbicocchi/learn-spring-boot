package com.baeldung.ls.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpELBeanA {

    @Value("#{2+3}")
    private Integer add;

    @Value("#{'Learn ' + 'Spring'}")
    private String addString;

    @Value("#{2 == 2}")
    private boolean equal;

    @Value("#{3 > 2 ? 'a' : 'b'}")
    private String ternary;

    @Value("#{spELBeanB.prop1}")
    private String otherBeanProperty;

    public Integer getAdd() {
        return add;
    }

    public void setAdd(Integer add) {
        this.add = add;
    }

    public String getAddString() {
        return addString;
    }

    public void setAddString(String addString) {
        this.addString = addString;
    }

    public boolean isEqual() {
        return equal;
    }

    public void setEqual(boolean equal) {
        this.equal = equal;
    }

    public String getTernary() {
        return ternary;
    }

    public void setTernary(String ternary) {
        this.ternary = ternary;
    }

    public String getOtherBeanProperty() {
        return otherBeanProperty;
    }

    public void setOtherBeanProperty(String otherBeanProperty) {
        this.otherBeanProperty = otherBeanProperty;
    }
}
