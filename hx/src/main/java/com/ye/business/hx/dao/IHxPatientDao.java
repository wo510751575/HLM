package com.ye.business.hx.dao;

import java.util.List;

import com.ye.business.hx.domain.HxPatient;
import com.ye.business.hx.dto.FindHxPatientPage;
import com.ye.business.hx.dto.HxPatientDto;
import com.ye.business.hx.dto.params.PatientParams;

public interface IHxPatientDao {
	int deleteByPrimaryKey(String code);

	int insert(HxPatient record);

	int insertSelective(HxPatient record);

	HxPatient selectByPrimaryKey(String code);

	int updateByPrimaryKeySelective(HxPatient record);

	int updateByPrimaryKey(HxPatient record);

	List<HxPatientDto> findHxPatients(FindHxPatientPage findHxPatientPage);

	List<HxPatientDto> findHxPatientPage(FindHxPatientPage findHxPatientPage);

	int findHxPatientPageCount(FindHxPatientPage findHxPatientPage);

	int queryPatientCount(PatientParams params);

	List<HxPatientDto> queryPatientList(PatientParams params);
}