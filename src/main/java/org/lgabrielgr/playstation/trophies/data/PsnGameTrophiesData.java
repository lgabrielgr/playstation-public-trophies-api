package org.lgabrielgr.playstation.trophies.data;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PsnGameTrophiesData {

    private String platform;
    private int progress;
    private String imgUrl;
    private String title;
    private String gameId;
    private PsnTrophiesData trophies;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public PsnTrophiesData getTrophies() {
        return trophies;
    }

    public void setTrophies(PsnTrophiesData trophies) {
        this.trophies = trophies;
    }

    public String toString() {

        return new ToStringBuilder(this)
                .append("platform", platform)
                .append("progress", progress)
                .append("game image url", imgUrl)
                .append("game title", title)
                .append("game id", gameId)
                .append("trophies", (trophies == null) ? StringUtils.EMPTY : trophies.toString())
                .build();

    }

}
