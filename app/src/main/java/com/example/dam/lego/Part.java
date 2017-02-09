package com.example.dam.lego;

/**
 * Created by dam on 2/2/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//Clase para extraer información más precisa de las partes
public class Part {

    @SerializedName("part_num")
    @Expose
    private String partNum;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("part_cat_id")
    @Expose
    private Integer partCatId;
    @SerializedName("part_url")
    @Expose
    private String partUrl;
    @SerializedName("part_img_url")
    @Expose
    private String partImgUrl;
    public String getPartNum() {
        return partNum;
    }
    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPartCatId() {
        return partCatId;
    }
    public void setPartCatId(Integer partCatId) {
        this.partCatId = partCatId;
    }
    public String getPartUrl() {
        return partUrl;
    }
    public void setPartUrl(String partUrl) {
        this.partUrl = partUrl;
    }
    public String getPartImgUrl() {
        return partImgUrl;
    }
    public void setPartImgUrl(String partImgUrl) {
        this.partImgUrl = partImgUrl;
    }
}
