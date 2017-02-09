package com.example.dam.lego;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//Clase para obtener la información de cada caja
public class Caja {

    @SerializedName("set_num")
    @Expose
    private String setNum;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("theme_id")
    @Expose
    private Integer themeId;
    @SerializedName("num_parts")
    @Expose
    private Integer numParts;
    @SerializedName("set_img_url")
    @Expose
    private String setImgUrl;
    @SerializedName("set_url")
    @Expose
    private String setUrl;
    @SerializedName("last_modified_dt")
    @Expose
    private String lastModifiedDt;
    public String getSetNum() {
        return setNum;
    }
    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getThemeId() {
        return themeId;
    }
    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }
    public Integer getNumParts() {
        return numParts;
    }
    public void setNumParts(Integer numParts) {
        this.numParts = numParts;
    }
    public String getSetImgUrl() {
        return setImgUrl;
    }
    public void setSetImgUrl(String setImgUrl) {
        this.setImgUrl = setImgUrl;
    }
    public String getSetUrl() {
        return setUrl;
    }
    public void setSetUrl(String setUrl) {
        this.setUrl = setUrl;
    }
    public String getLastModifiedDt() {
        return lastModifiedDt;
    }
    public void setLastModifiedDt(String lastModifiedDt) {
        this.lastModifiedDt = lastModifiedDt;
    }
}
