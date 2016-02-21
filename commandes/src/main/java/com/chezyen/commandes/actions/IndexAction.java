package com.chezyen.commandes.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private static Logger log = LogManager.getLogger(IndexAction.class);
	public String index() {
		log.info("Appel de index");
		return SUCCESS;
	}
}
