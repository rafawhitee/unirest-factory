import java.util.Arrays;
import java.util.List;

import dto.ChaveValorDTO;
import dto.ConsultaCepDTO;
import dto.RepositorioGithubDTO;
import kong.unirest.JsonNode;
import util.UnirestUtil;
import util.UnirestUtilImpl;

public class Principal {

	public static void main(String[] args) {
		// UnirestUtil.baseUrl("https://api.github.com");
		UnirestUtil unirest = new UnirestUtilImpl();

		// Testando pegando como JsonBody e sem BaseURL
		JsonNode jsonNode = unirest.getJsonBody("https://api.github.com/users/rafawhitee/repos");
		System.out.println("Tamanho: " + jsonNode.getArray().length());
		
		System.out.println("");
		System.out.println("//// TESTANDO RETORNANDO COMO DTO //////");
		System.out.println("");

		// Testando como classe e sem BaseURL
		RepositorioGithubDTO[] arrayRepositoryGithub = unirest
				.getBodyObject("https://api.github.com/users/rafawhitee/repos", RepositorioGithubDTO[].class);
		List<RepositorioGithubDTO> listaRepositorio = Arrays.asList(arrayRepositoryGithub);
		for (RepositorioGithubDTO dto : listaRepositorio)
			System.out.println(dto.getId() + " - " + dto.getNode_id() + " - " + dto.getName());

		System.out.println("");
		System.out.println("//// TESTANDO COM ROUTE PARAMS //////");
		System.out.println("");
		
		// Testando como classe com pathParams
		ChaveValorDTO chaveUsernameRouteParam = new ChaveValorDTO("username", "rafawhitee");
		RepositorioGithubDTO[] arrayRepositoryGithubWithRoute = unirest.getBodyObject(
				"https://api.github.com/users/{username}/repos", null, Arrays.asList(chaveUsernameRouteParam),
				RepositorioGithubDTO[].class);
		List<RepositorioGithubDTO> listaRepositorioWithRoute = Arrays.asList(arrayRepositoryGithubWithRoute);
		for (RepositorioGithubDTO dto : listaRepositorioWithRoute)
			System.out.println(dto.getId() + " - " + dto.getNode_id() + " - " + dto.getName());
		
		
		System.out.println("");
		System.out.println("//// TESTANDO VIACEP //////");
		System.out.println("");
		
		ChaveValorDTO chaveCepRouteParam = new ChaveValorDTO("cep", "25515125");
		ConsultaCepDTO consultaCep = unirest.getBodyObject("https://viacep.com.br/ws/{cep}/json/", null, Arrays.asList(chaveCepRouteParam), ConsultaCepDTO.class);
		System.out.println(consultaCep);

	}

}