package com.lj.business.cm.emus;

/**
 * 
 * 
 * 类说明：素材分类
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
public enum MaterialType {
	IMAGE("1", "图文"), VIDEO("2", "视频"),LINK("3","链接"),TEXT("4","文字");

    private String type;
    private String name;

    private MaterialType(String type, String name) {
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
