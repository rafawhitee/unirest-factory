package util;

import kong.unirest.HttpRequestWithBody;
import kong.unirest.Unirest;

public interface UnirestDeleteUtil extends GenericsUnirestUtil {

	default HttpRequestWithBody delete(String url) {
		return (HttpRequestWithBody) putHeaders(Unirest.delete(url));
	}
	
}