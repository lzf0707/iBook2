package com.mall.security.browser.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

public class BrowserExpiredSessionStrategy implements SessionInformationExpiredStrategy{

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		// TODO Auto-generated method stub
		event.getResponse().setContentType("application/josn;charset=UTF-8");
		event.getResponse().getWriter().write("并发登录！");
	}

}
