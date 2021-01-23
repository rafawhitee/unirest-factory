package controller;

import java.util.ArrayList;
import java.util.List;

import dto.ChaveValorDTO;

public class Controller implements GenericsController {

	private String baseUrl;
	protected static String clientId;
	protected static String clientSecret;
	private String env = "";
	private String appUrl;
	private List<ChaveValorDTO> headers = new ArrayList<>();

	public Controller() {
		setBaseUrl("baseUrl_here");
		clientId = "clientId_here";
		clientSecret = "clientSecret_here";
		addHeader("client_id", clientId);
	}

	@Override
	public List<ChaveValorDTO> getHeaders() {
		return headers;
	}

	@Override
	public void setHeaders(List<ChaveValorDTO> headers) {
		this.headers = headers;
	}

	@Override
	public String getBaseUrl() {
		return baseUrl;
	}

	@Override
	public String getClientId() {
		return clientId;
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public String getEnv() {
		return env;
	}

	@Override
	public String getAppUrl() {
		return appUrl;
	}

	@Override
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
		putBaseUrl(baseUrl);
	}

	@Override
	public void setEnv(String env) {
		this.env = env;
	}

	@Override
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

}