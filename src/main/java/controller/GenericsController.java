package controller;

import util.UnirestUtil;

public interface GenericsController extends UnirestUtil {
	
	String getBaseUrl();
	String getClientId();
	String getClientSecret();
	String getEnv();
	String getAppUrl();

}