package com.lj.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lj.business.ai.dto.CustomProblemDto;
import com.lj.business.ai.service.ICustomProblemService;
import com.lj.business.dao.CustomProblemDao;

@Service
public class CustomProblemServiceImpl implements ICustomProblemService {

    @Resource
    CustomProblemDao customProblemDao;

    @Override
    public void addCustomProblem(CustomProblemDto customProblemDto) {
            customProblemDao.addCustomProblem(customProblemDto);
    }
    @Override
    public void updateCustomProblem(CustomProblemDto customProblemDto) {
            customProblemDao.updateCustomProblem(customProblemDto);
    }
    @Override
    public void resolveCustomProblem(CustomProblemDto customProblemDto) {
    }

    @Override
    public Integer findCustomProblemMaxSeqBySessionId(String sessionId) {
            return customProblemDao.findCustomProblemMaxSeqBySessionId(sessionId);
    }
	@Override
	public CustomProblemDto getCustomProblemByCode(String code) {
		 return customProblemDao.getCustomProblemByCode(code);
	}
}
