package org.lgabrielgr.playstation.trophies.data;

public class PsnUserCommonData {

    private String handle;
    private String avatarUrl;
    private int isPlusUser;
    private int curLevel;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getIsPlusUser() {
        return isPlusUser;
    }

    public void setIsPlusUser(int isPlusUser) {
        this.isPlusUser = isPlusUser;
    }

    public int getCurLevel() {
        return curLevel;
    }

    public void setCurLevel(int curLevel) {
        this.curLevel = curLevel;
    }
}
