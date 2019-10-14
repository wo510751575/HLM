package com.lj.business.cm.dao;

import java.util.List;

import com.lj.business.cm.domain.FriendsLinkMaterial;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialPage;
import com.lj.business.cm.friendsLinkMaterial.FindFriendsLinkMaterialPageReturn;

public interface IFriendsLinkMaterialDao {
    int deleteByPrimaryKey(String code);

    int insert(FriendsLinkMaterial record);

    int insertSelective(FriendsLinkMaterial record);

    FriendsLinkMaterial selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(FriendsLinkMaterial record);

    int updateByPrimaryKey(FriendsLinkMaterial record);

	List<FindFriendsLinkMaterialPageReturn> findFriendsLinkMaterialPage(FindFriendsLinkMaterialPage findFriendsLinkMaterialPage);

	int findFriendsLinkMaterialPageCount(FindFriendsLinkMaterialPage findFriendsLinkMaterialPage);
}