package com.lj.oms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lj.oms.entity.sys.Office;
import com.lj.oms.entity.sys.User;
import com.lj.oms.service.OfficeHessianService;
import com.lj.oms.service.sys.OfficeService;
import com.lj.oms.service.sys.SystemService;
import com.lj.oms.utils.UserUtils;

@Service
@Transactional(readOnly = true)
public class OfficeHessianServiceImpl implements OfficeHessianService{
	
	@Resource
	private OfficeService officeService;
	@Resource
	private SystemService systemService;

	@Override
	public Office findByShopNo(String shopNo) {
		return officeService.findByShopNo(shopNo);
	}

	@Override
	public List<Office> findThirdOfficeList(String userId) {
		User user = systemService.getUser(userId);
		//Office office = user.getOffice();
		Office office = officeService.findOffice(user.getOffice().getId());
		
		List<Office> list = new ArrayList<Office>();
		if(office != null && Office.GRADE_3.equals(office.getGrade())){
			list.add(office);
			return list;
		}
		if(office != null && Office.GRADE_2.equals(office.getGrade()) ){
			list = officeService.findThirdOfficeList(office);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.lj.oms.service.OfficeHessianService#findOfficeByMerchantNo(java.lang.String)
	 */
	@Override
	public Office findOfficeByMerchantNo(String merchantNo) {
		 return officeService.findOffice(merchantNo);
	}

	@Transactional(readOnly = false)
    @Override
    public void addOffice(Office office) {
        officeService.save(office);
    }

	@Override
	public List<Office> findMerchants() {
		return officeService.findMerchants();
	}

	@Override
	public List<Office> findMerchantsByOffice(Office office) {
		// TODO Auto-generated method stub
		return officeService.findMerchantsByOffice(office);
	}
	
	

	@Override
	public List<Office> findOfficeListByUserId(String userId) {
		return UserUtils.getOfficeListByUserId(userId);
	}
}
