package com.lj.business.ai.service;

import com.lj.business.ai.dto.CustomProblemDto;

public interface ICustomProblemService {

    /**
     * 新增客户问题
     *
     * @param customProblemDto
     */
     void addCustomProblem(CustomProblemDto customProblemDto);

    /**
     *  修改客户问题
     * @param customProblemDto
     */
     void updateCustomProblem(CustomProblemDto customProblemDto);


    /**
     * 解决客户问题
     *
     * @param customProblemDto
     */
     void resolveCustomProblem(CustomProblemDto customProblemDto);



     Integer findCustomProblemMaxSeqBySessionId(String sessionId);
     
     
     
     CustomProblemDto getCustomProblemByCode(String code);


}
