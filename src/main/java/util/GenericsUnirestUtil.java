package util;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
	static final String DEFAULT_CONTENT_TYPE = "application/json";

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

	default void baseUrl(String baseUrl) {
		if (Objects.nonNull(baseUrl))
			getConfig().defaultBaseUrl(baseUrl);
		else
			throw new RuntimeException("base url can't be null");
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

	default HttpRequest<?> putDefaultHeaders(HttpRequest<?> httpRequest) {
		return putHeaders(httpRequest, getDefaultHeaders());
	}

	default HttpRequest<?> putHeaders(HttpRequest<?> httpRequest, List<ChaveValorDTO> headers) {
		return putChaveValorOnHttpRequest(headers, httpRequest, HEADER);
	}

}