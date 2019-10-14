package com.lj.business.dao;

import com.lj.business.ai.dto.CustomProblemDto;

public interface CustomProblemDao {
    /**
     * 新增客户问题
     * @param customProblemDto
     */
    void addCustomProblem(CustomProblemDto customProblemDto);

    /**
     * 更新客户问题
     * @param customProblemDto
     */
    void updateCustomProblem(CustomProblemDto customProblemDto);

    /**
     * 通过会话ID查询客户相关提问
     * @param sessionId
     * @return
     */
    Integer findCustomProblemMaxSeqBySessionId(String sessionId);
    
    
    CustomProblemDto getCustomProblemByCode(String code);
}
