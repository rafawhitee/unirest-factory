package controller;

import util.UnirestUtil;

public interface GenericsController extends UnirestUtil {
	
	String getBaseUrl();
	void setBaseUrl(String baseUrl);
	
	String getClientId();
	String getClientSecret();
	
	String getEnv();
	void setEnv(String env);
	
	String getAppUrl();
	void setAppUrl(String appUrl);

}