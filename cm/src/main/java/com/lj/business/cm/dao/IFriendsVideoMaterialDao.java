package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.domain.FriendsVideoMaterial;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterialPage;
import com.lj.business.cm.dto.friends.FindFriendsVideoMaterialPageReturn;

public interface IFriendsVideoMaterialDao {
    int deleteByPrimaryKey(String code);

    int insert(FriendsVideoMaterial record);

    int insertSelective(FriendsVideoMaterial record);

    FriendsVideoMaterial selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(FriendsVideoMaterial record);

    int updateByPrimaryKey(FriendsVideoMaterial record);
    
	List<FindFriendsVideoMaterialPageReturn> findFriendsVideoMaterialPage(FindFriendsVideoMaterialPage findFriendsVideoMaterialPage);

	int findFriendsVideoMaterialPageCount(FindFriendsVideoMaterialPage findFriendsVideoMaterialPage);

}