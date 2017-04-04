package com.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.DaoSupport;
import com.entity.Info;
import com.entity.Page;

@SuppressWarnings("unchecked")
@Service("infoService")
public class InfoService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	public List<Info> listPageInfo(Page page) throws Exception {
		return (List<Info>) dao.findForList("InfoMapper.listPageInfo", page);
	}

}
