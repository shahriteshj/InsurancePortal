package com.jp.insurance.interceptors;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.InternalResourceView;

public class JstlView extends InternalResourceView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dispatcherPath = prepareForRendering(request, response);

		// set original view being asked for as a request parameter
		// request.setAttribute("partial",
		// dispatcherPath.substring(dispatcherPath.lastIndexOf("/") + 1));
		request.setAttribute("partial", dispatcherPath);
		System.out.println(dispatcherPath.substring(dispatcherPath.lastIndexOf("/") + 1));
		System.out.println(dispatcherPath);

		request.setAttribute("model", model);
		System.out.println(model.get("query"));
		// force everything to be template.jsp
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/template.jsp");
		rd.include(request, response);
	}
}