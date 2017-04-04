package com.service.sms;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dao.DaoSupport;
import com.entity.Page;
import com.util.PageData;

@SuppressWarnings("unchecked")
@Service("smsService")
public class SmsService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	public List<PageData> listPageHttpJoinRecord(Page page) throws Exception {
		return (List<PageData>) dao.findForList("SmsMapper.listPageHttpJoinRecord", page);
	}

	public List<PageData> listAllAPIType() throws Exception {
		return (List<PageData>) dao.findForList("SmsMapper.listAllAPIType", null);
	}

	public void saveHttpJoinRecord(PageData pd, String opType) throws Exception {
		if(opType.equals("edit")){
			dao.update("SmsMapper.updateHttpJoinRecord", pd);
		}else{
			dao.save("SmsMapper.saveHttpJoinRecord", pd);
		}
	}

	public PageData getHttpJoinRecordById(String jr_id) throws Exception {
		return (PageData) dao.findForObject("SmsMapper.getHttpJoinRecordById", jr_id);
	}

	public void deleteHttpJoinRecordById(String jr_id) throws Exception {
		dao.delete("SmsMapper.deleteHttpJoinRecordById", jr_id);
	}
	
	
}
