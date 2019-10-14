package com.lj.business.api.dto.friend;

public class AddLikeRequestDto {

    private String friendsId;

    /**
     * 点赞人昵称
     */
    private String nickName;

    /**
     * 点赞人微信号
     */
    private String userName;

    private String noWxShop;



    public String getNoWxShop() {
        return noWxShop;
    }

    public void setNoWxShop(String noWxShop) {
        this.noWxShop = noWxShop;
    }

    public String getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(String friendsId) {
        this.friendsId = friendsId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AddLikeRequestDto{");
        sb.append("friendsId='").append(friendsId).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", noWxShop='").append(noWxShop).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
