package io.mojohao.soapui_ng.entity;

/**
 * 用于查询分类及对应数目，方便前台展示
 */
public class ChartTypeDto {
    private String type;
    private int number;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ChartTypeDto() {
    }

    public ChartTypeDto(String type, int number) {
        this.type = type;
        this.number = number;
    }
}
