package com.ye.business.ad.dao;

import java.util.List;

import com.ye.business.ad.domain.Bill;
import com.ye.business.ad.dto.BillDto;
import com.ye.business.ad.dto.FindBillPage;

public interface IBillDao {
    int insert(Bill record);

    int insertSelective(Bill record);

	List<BillDto> findBills(FindBillPage findBillPage);

	int updateByPrimaryKeySelective(Bill bill);

	Bill selectByPrimaryKey(String code);

	List<BillDto> findBillPage(FindBillPage findBillPage);

	int findBillPageCount(FindBillPage findBillPage);
}