package com.lj.business.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.domain.Value;
import org.nlpcn.commons.lang.tire.library.Library;
import org.springframework.stereotype.Component;

import com.lj.business.ai.dto.AiCacheDto;
import com.lj.business.ai.emuns.WordRecognitionEnums;

@Component
public class WordsUtil {

    /**
     * 分词
     * @param content
     * @return
     */
    public  List<String> splitWords(String content){
        List<String> list = new ArrayList<>();
        Result result  = NlpAnalysis.parse(content);
        Iterator<Term> it = result.getTerms().iterator();
        while(it.hasNext()){
            Term term = (Term) it.next();
            if(isMatch(term.getNatureStr())) {
                list.add(term.getName());
            }
        }
        return list;
    }
    
    
    
    public static  Forest  flushDic(List<Value> word){
    		Forest forest = Library.makeForest(word);
    		return forest;
    }
    
 

    /**
     * 词性匹配
     * @param nature
     * @return
     */
    private   boolean isMatch(String nature){
        if(WordRecognitionEnums.N.getKey().equals(nature)){
            return true;
        }else if(WordRecognitionEnums.NS.getKey().equals(nature)){
            return true;
        }else if(WordRecognitionEnums.V.getKey().equals(nature)){
            return true;
        }else if(WordRecognitionEnums.VN.getKey().equals(nature)){
            return  true;
        }else  if(WordRecognitionEnums.NR.getKey().equals(nature)){
            return true;
        }
        return false;
    }
    /**
	 * 确认解决问题
	 * 
	 * @param sessionId
	 * @param content
	 * @return
	 */
	public boolean isConfirm(AiCacheDto aiCacheDto ,String content) {
		if(aiCacheDto.getMemberNo()==null){
			//LOG.debug("isConfirm   session is vali :{}",sessionId);
			return false;
		}
		

		return false;
	}
   

    public static void main(String[] args) {
    	 
    }

}
