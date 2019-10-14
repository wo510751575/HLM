package com.lj.business.member.dao;

import java.util.List;
import java.util.Map;

import com.lj.business.member.domain.PmType;
import com.lj.business.member.dto.FindPmType;
import com.lj.business.member.dto.FindPmTypeIndex;
import com.lj.business.member.dto.FindPmTypeIndexReturn;
import com.lj.business.member.dto.FindPmTypePage;
import com.lj.business.member.dto.FindPmTypePageReturn;
import com.lj.business.member.dto.FindUnContactPmType;

public interface IPmTypeDao {
	
	/**
	 * 
	 *
	 * 方法说明：查找未分组信息_自动分组用
	 *
	 * @param map
	 * @return
	 *
	 * @author 彭阳 CreateDate: 2017年10月30日
	 *
	 */
	List<Map<String,String>> selectUngroupInfo(Map<String,String> map);
	
    int deleteByPrimaryKey(String code);

    int insert(PmType record);

    int insertSelective(PmType record);
    

    PmType selectByPrimaryKey(String code);
    
    PmType selectByParamKey(PmType pmType);
    
    PmType selectByMP(FindPmType findPmType);
    
    List<FindPmTypeIndexReturn>  findPmTypeIndex(FindPmTypeIndex findPmTypeIndex);

    int updateByPrimaryKeySelective(PmType record);

    int updateByPrimaryKey(PmType record);
    /**
     * 
     *
     * 方法说明：分页查询
     *
     * @param findPmTypePage
     * @return
     *
     * @author 邹磊 CreateDate: 2017年6月28日
     *
     */
	List<FindPmTypePageReturn> findPmTypePage(FindPmTypePage findPmTypePage);
	/**
	 * 
	 *
	 * 方法说明：分页查询显示条数
	 *
	 * @param findPmTypePage
	 * @return
	 *
	 * @author 邹磊 CreateDate: 2017年6月28日
	 *
	 */
	int findPmTypePageCount(FindPmTypePage findPmTypePage);
	
	
	/**
	 * 
	 *
	 * 方法说明：获取客户分组信息 新增
	 *
	 * @param merchantNo
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月14日
	 *
	 */
	List<PmType> inqueryNewMemberOutOffGroupInfo(String merchantNo);
	
	/**
	 * 
	 *
	 * 方法说明：查询客户分类 除去交叉、成单、暂停、紧急
	 *
	 * @param merchantNo
	 * @return
	 *
	 * @author 冯辉 CreateDate: 2017年7月14日
	 *
	 */
	List<PmType> inqueryModifyMemberOutOffGroupInfo(String merchantNo);
	
	
	/**
	 * 
	 *
	 * 方法说明：条件查询返回
	 *
	 * @param findPmTypePageReturn
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年7月4日
	 *
	 */
	List<FindPmTypePageReturn> findPmTypePages(FindPmTypePageReturn findPmTypePageReturn);
	

	/**
	 * 
	 *
	 * 方法说明：查询未联系顾客分组信息
	 *
	 * @param findUnContactPmType
	 * @return
	 *
	 * @author 许新龙 CreateDate: 2017年12月7日
	 *
	 */
    List<FindPmTypeIndexReturn> findUnContactMemberPmTypes(FindUnContactPmType findUnContactPmType);
	
    /**
     * 获取未分组客户数量
     * @param findPmTypeIndex
     * @return
     */
    int findCountByUngroup(FindPmTypeIndex findPmTypeIndex);
}