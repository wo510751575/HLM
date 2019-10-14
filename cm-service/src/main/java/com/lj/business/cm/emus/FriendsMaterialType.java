package com.lj.business.cm.emus;

/**
 * 
 * 
 * 类说明：朋友圈素材类型
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 许新龙
 * 
 *         CreateDate: 2017年12月23日
 */
public enum FriendsMaterialType {
    MAINTAIN("1", "朋友圈维护素材"), ADVERTISEMENT("2", "朋友圈广告素材");

    private String type;
    private String name;

    private FriendsMaterialType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
