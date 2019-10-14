package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.BillSnapshot;
import com.ye.business.hx.dto.BillSnapshotDto;
import com.ye.business.hx.dto.FindBillSnapshotPage;

public interface IBillSnapshotDao {
    int deleteByPrimaryKey(String code);

    int insert(BillSnapshot record);

    int insertSelective(BillSnapshot record);

    BillSnapshot selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(BillSnapshot record);

    int updateByPrimaryKey(BillSnapshot record);
    
    List<BillSnapshotDto> findBillSnapshots(FindBillSnapshotPage findBillSnapshotPage);
    
    List<BillSnapshotDto> findBillSnapshotPage(FindBillSnapshotPage findBillSnapshotPage);
    
    int findBillSnapshotPageCount(FindBillSnapshotPage findBillSnapshotPage);
    
    BillSnapshot selectByOperateCode(String operateCode);
}