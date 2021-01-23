package util;

import kong.unirest.HttpRequestWithBody;
import kong.unirest.Unirest;

public interface UnirestPutUtil extends GenericsUnirestUtil {

	default HttpRequestWithBody put(String url) {
		return (HttpRequestWithBody) putDefaultHeaders(Unirest.put(url));
	}
	
}