package com.qxt.bysj.domain;

public class Product {
    private Integer id;
    private String productName;
    private String price;
    private String pic;
    private Integer brandId;
    private Integer typeId;
    private Integer hot;

    //非DB
    private String brandname;
    private String brandpic;
    private String typeName;
    private Integer[] brandIds;
    private Integer[] typeIds;

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer[] getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(Integer[] brandIds) {
        this.brandIds = brandIds;
    }

    public Integer[] getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(Integer[] typeIds) {
        this.typeIds = typeIds;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getBrandpic() {
        return brandpic;
    }

    public void setBrandpic(String brandpic) {
        this.brandpic = brandpic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
