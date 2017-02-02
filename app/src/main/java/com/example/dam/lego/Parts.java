package com.example.dam.lego;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parts {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("inv_part_id")
    @Expose
    private Integer invPartId;
    @SerializedName("part")
    @Expose
    private Part part;
    @SerializedName("color")
    @Expose
    private Color color;
    @SerializedName("set_num")
    @Expose
    private String setNum;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("is_spare")
    @Expose
    private Boolean isSpare;
    @SerializedName("element_id")
    @Expose
    private String elementId;
    @SerializedName("num_sets")
    @Expose
    private Integer numSets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvPartId() {
        return invPartId;
    }

    public void setInvPartId(Integer invPartId) {
        this.invPartId = invPartId;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getIsSpare() {
        return isSpare;
    }

    public void setIsSpare(Boolean isSpare) {
        this.isSpare = isSpare;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public Integer getNumSets() {
        return numSets;
    }

    public void setNumSets(Integer numSets) {
        this.numSets = numSets;
    }


}