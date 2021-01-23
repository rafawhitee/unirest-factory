package util;

import java.util.List;
import java.util.Objects;

import dto.ChaveValorDTO;
import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public interface UnirestGetUtil extends GenericsUnirestUtil {

	default GetRequest get(String url) {
		return (GetRequest) putDefaultHeaders(Unirest.get(url));
	}

	// Retorna a requisição como HttpResponse Tipado
	default <T> HttpResponse<T> getAsObject(String url, Class<T> classeGenerics) {
		GetRequest get = get(url);
		HttpResponse<T> objectResponse = get.asObject(classeGenerics);
		return objectResponse;
	}

	// Retorna a classe tipada da requisição
	default <T> T getBodyObject(String url, Class<T> classeGenerics) {
		HttpResponse<T> objectResponse = getAsObject(url, classeGenerics);
		return objectResponse.getBody();
	}

	default GetRequest get(String url, List<ChaveValorDTO> queriesString, List<ChaveValorDTO> routeParams) {
		GetRequest get = get(url);
		get = (GetRequest) putChaveValorOnHttpRequest(queriesString, get, QUERY_STRING);
		get = (GetRequest) putChaveValorOnHttpRequest(routeParams, get, ROUTE_PARAM);
		return get;
	}

	default <T> HttpResponse<T> getAsObject(String url, List<ChaveValorDTO> queriesString,
			List<ChaveValorDTO> routeParams, Class<T> classeGenerics) {
		GetRequest get = get(url, queriesString, routeParams);
		HttpResponse<T> objectResponse = get.asObject(classeGenerics);
		return objectResponse;
	}

	default <T> T getBodyObject(String url, List<ChaveValorDTO> queriesString, List<ChaveValorDTO> routeParams,
			Class<T> classeGenerics) {
		HttpResponse<T> objectResponse = getAsObject(url, queriesString, routeParams, classeGenerics);
		return objectResponse.getBody();
	}

	default HttpResponse<JsonNode> getJson(String url) {
		GetRequest getRequest = get(url);
		return json(getRequest);
	}

	default HttpResponse<JsonNode> getJson(String url, List<ChaveValorDTO> queriesString,
			List<ChaveValorDTO> routeParams) {
		GetRequest getRequest = get(url, queriesString, routeParams);
		return json(getRequest);
	}

	default JsonNode getJsonBody(String url) {
		HttpResponse<JsonNode> responseJson = getJson(url);
		if (Objects.nonNull(responseJson))
			return responseJson.getBody();

		return null;
	}

	default JsonNode getJsonBody(String url, List<ChaveValorDTO> queriesString, List<ChaveValorDTO> routeParams) {
		HttpResponse<JsonNode> responseJson = getJson(url, queriesString, routeParams);
		if (Objects.nonNull(responseJson))
			return responseJson.getBody();

		return null;
	}

}