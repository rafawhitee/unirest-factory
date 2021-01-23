package util;

import java.util.List;

import dto.ChaveValorDTO;

public class UnirestUtilImpl implements UnirestUtil {

	public UnirestUtilImpl() {

	}

	public UnirestUtilImpl(String baseUrl) {
		putBaseUrl(baseUrl);
	}

	@Override
	public List<ChaveValorDTO> getHeaders() {
		return null;
	}

	@Override
	public void setHeaders(List<ChaveValorDTO> headers) {
	
	}

}