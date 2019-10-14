package com.lj.oms.service.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lj.oms.dao.sys.OfficeDao;
import com.lj.oms.entity.sys.Office;
import com.lj.oms.service.TreeService;
import com.lj.oms.utils.UserUtils;

/**
 * 机构Service
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {

	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null && !office.getId().isEmpty()){
			office.setParentIds("%"+office.getParentIds()+"%");
			return dao.findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	public List<Office> findIdList(){
		return dao.findIdList();
	}
	public Office findByShopNo(String shopNo){
		return dao.findByShopNo(shopNo);
	}
	
	public List<Office> findThirdOfficeList(Office office){
		return dao.findThirdOfficeList(office.getId());
	}
	
	public Office findOffice(String officeId){
		return dao.get(officeId);
	}
	
	/**
	 * 获取商户列表
	 * 1.直属ROOT下面的机构
	 * @return
	 */
	public List<Office> findMerchants(){
		return dao.findMerchants();
	}
	
	/**
	 * 查询所有商户（门诊）集合
	 * @param office null 则查询所有 商户<br>
	 *  office.name 非空 则按name模糊搜索 商户<br>
	 *  office.address 非空 则按address模糊搜索 商户
	 * @return
	 * @author 刘红艳 2019年3月16日 add by 焕新：查询所有门诊集合
	 */
	public List<Office> findMerchantsByOffice(Office office){
		return dao.findMerchantsByOffice(office);
	}
	
}
