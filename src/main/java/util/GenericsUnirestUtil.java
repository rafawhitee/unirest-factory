package util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import dto.ChaveValorDTO;
import kong.unirest.Config;
import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public interface GenericsUnirestUtil {

	static final String QUERY_STRING = "queryString";
	static final String HEADER = "header";
	static final String ROUTE_PARAM = "routeParam";
	static final String DEFAULT_ACCEPT = "application/json";
	static final String DEFAULT_CONTENT_TYPE = "application/json;charset=utf-8";

	// Obriga as concretas a implementar
	List<ChaveValorDTO> getHeaders();

	void setHeaders(List<ChaveValorDTO> headers);

	default void validateHeaders() {
		if (Objects.isNull(getHeaders()))
			setHeaders(new ArrayList<ChaveValorDTO>());
	}

	default void addHeader(ChaveValorDTO chaveValor) {
		validateHeaders();
		getHeaders().add(chaveValor);
	}

	default void addHeader(String chave, String valor) {
		addHeader(new ChaveValorDTO(chave, valor));
	}

	default void addHeaders(List<ChaveValorDTO> headers) {
		if (Objects.nonNull(headers) && !headers.isEmpty()) {
			for (ChaveValorDTO currentChaveValor : headers)
				addHeader(currentChaveValor);
		}
	}

	default void addHeaders(Map<String, String> map) {
		Set<String> keys = map.keySet();
		if (Objects.nonNull(keys) && !keys.isEmpty()) {
			for (String currentKey : keys)
				addHeader(currentKey, map.get(currentKey));
		}
	}

	default HttpRequest<?> putChaveValorOnHttpRequest(List<ChaveValorDTO> chavesAndValores, HttpRequest<?> httpRequest,
			String tipo) {

		// Valida os parâmetros
		if (Objects.nonNull(chavesAndValores) && !chavesAndValores.isEmpty() && Objects.nonNull(httpRequest)
				&& StringUtils.isNotBlank(tipo)) {

			// Faz o For na lista de ChaveValor
			for (ChaveValorDTO chaveValorDTO : chavesAndValores) {

				// Checa se a chave e valor estão preenchidos
				if (Objects.nonNull(chaveValorDTO.getChave()) && Objects.nonNull(chaveValorDTO.getValor())) {
					// Vê o tipo que é pra preencher no httpRequest
					switch (tipo) {
					case HEADER:
						httpRequest = httpRequest.header(chaveValorDTO.getChave(), chaveValorDTO.getValor().toString());
						break;
					case ROUTE_PARAM:
						httpRequest = httpRequest.routeParam(chaveValorDTO.getChave(),
								chaveValorDTO.getValor().toString());
						break;
					case QUERY_STRING:
						httpRequest = httpRequest.queryString(chaveValorDTO.getChave(), chaveValorDTO.getValor());
						break;
					}
				}

			}
		}
		return httpRequest;
	}

	default Config getConfig() {
		return Unirest.config();
	}

	default void putBaseUrl(String baseUrl) {
		if (Objects.nonNull(baseUrl))
			getConfig().defaultBaseUrl(baseUrl);
	}

	default String getKeyFromResponse(HttpResponse<JsonNode> response, String key) {
		if (Objects.nonNull(response) && response.isSuccess()) {
			JsonNode body = response.getBody();
			return body.getObject().getString(key);
		}
		return null;
	}

	default HttpResponse<JsonNode> json(HttpRequest<?> httpRequest) {
		return httpRequest.asJson();
	}

	default HttpResponse<String> string(HttpRequest<?> httpRequest) {
		return httpRequest.asString();
	}

	default HttpResponse<File> file(HttpRequest<?> httpRequest, String path) {
		return httpRequest.asFile(path);
	}

	default HttpResponse<File> file(HttpRequest<?> httpRequest) {
		return file(httpRequest, System.getProperty("user.dir"));
	}

	default List<ChaveValorDTO> getDefaultHeaders() {
		return Arrays.asList(new ChaveValorDTO("accept", DEFAULT_ACCEPT),
				new ChaveValorDTO("content-type", DEFAULT_CONTENT_TYPE));
	}

	default HttpRequest<?> putHeaders(HttpRequest<?> httpRequest) {
		List<ChaveValorDTO> headers = getHeaders();
		if (Objects.isNull(headers))
			setHeaders(new ArrayList<ChaveValorDTO>());

		getHeaders().addAll(getDefaultHeaders());
		return putChaveValorOnHttpRequest(getHeaders(), httpRequest, HEADER);
	}

	default HttpRequest<?> putHeaders(HttpRequest<?> httpRequest, List<ChaveValorDTO> headers) {
		return putChaveValorOnHttpRequest(headers, httpRequest, HEADER);
	}

}