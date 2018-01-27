package org.lgabrielgr.playstation.trophies.data;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PsnUserData extends PsnUserCommonData {

    private int progress;
    private PsnTrophiesData trophies;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public PsnTrophiesData getTrophies() {
        return trophies;
    }

    public void setTrophies(PsnTrophiesData trophies) {
        this.trophies = trophies;
    }

    public String toString() {

        return new ToStringBuilder(this)
                .append("id", getHandle())
                .append("avatar url", getAvatarUrl())
                .append("is psn plus user?", BooleanUtils.toBoolean(getIsPlusUser()))
                .append("current level", getCurLevel())
                .append("progress", progress)
                .append("trophies", (trophies == null) ? StringUtils.EMPTY : trophies.toString())
                .build();

    }

}
