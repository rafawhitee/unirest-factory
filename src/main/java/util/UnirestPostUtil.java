package util;

import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public interface UnirestPostUtil extends GenericsUnirestUtil {

	default HttpRequestWithBody post(String url) {
		return (HttpRequestWithBody) putHeaders(Unirest.post(url));
	}

	default HttpRequestWithBody post(String url, JSONObject body) {
		HttpRequestWithBody request = post(url);
		return (HttpRequestWithBody) putHeaders(request.body(body));
	}

	default HttpRequestWithBody post(String url, String body) {
		HttpRequestWithBody request = post(url);
		return (HttpRequestWithBody) putHeaders(request.body(body));
	}

	default HttpResponse<JsonNode> postJson(String url, String body) {
		HttpRequestWithBody post = post(url, body);
		return json(post);
	}
	
}