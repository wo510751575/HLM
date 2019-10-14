package com.lj.oms.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ape.common.persistence.annotation.MyBatisDao;
import com.lj.oms.dao.TreeDao;
import com.lj.oms.entity.sys.Office;

/**
 * 机构DAO接口
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	/**
	 * 
	 *
	 * 方法说明：机构列表(父节点为1的机构)
	 *
	 * @return
	 *
	 * @author 罗书明 CreateDate: 2017年9月19日
	 *
	 */
	List<Office> findIdList();
	Office findByShopNo(String shopNo);
	
	List<Office> findThirdOfficeList(@Param("officeId") String officeId);
	
	/**
	 * 获取商户列表
	 * 1.直属ROOT下面的机构
	 * @return
	 */
	List<Office> findMerchants();
	
	List<Office> findMerchantsByOffice(Office office);
}
