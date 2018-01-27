package org.lgabrielgr.playstation.trophies.data;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonGetter;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class PsnUserTrophiesData extends PsnUserCommonData {

    private int overallProgress;
    private List<PsnGameTrophiesData> list;

    @JsonProperty(value = "overallprogress")
    public int getOverallProgress() {
        return overallProgress;
    }

    public void setOverallProgress(int overallProgress) {
        this.overallProgress = overallProgress;
    }

    public List<PsnGameTrophiesData> getList() {
        return list;
    }

    public void setList(List<PsnGameTrophiesData> list) {
        this.list = list;
    }

    public String toString() {

        return new ToStringBuilder(this)
                .append("id", getHandle())
                .append("avatar url", getAvatarUrl())
                .append("is psn plus user?", BooleanUtils.toBoolean(getIsPlusUser()))
                .append("current level", getCurLevel())
                .append("overall progress", overallProgress)
                .append("trophies list", StringUtils.join(list, ","))
                .build();

    }

}
