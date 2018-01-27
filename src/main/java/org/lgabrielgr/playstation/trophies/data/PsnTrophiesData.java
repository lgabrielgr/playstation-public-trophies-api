package org.lgabrielgr.playstation.trophies.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PsnTrophiesData {

    private int bronze;
    private int silver;
    private int gold;
    private int platinum;

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPlatinum() {
        return platinum;
    }

    public void setPlatinum(int platinum) {
        this.platinum = platinum;
    }

    public String toString() {

        return new ToStringBuilder(this)
                .append("bronze", bronze)
                .append("silver", silver)
                .append("gold", gold)
                .append("platinum", platinum)
                .build();

    }

}
