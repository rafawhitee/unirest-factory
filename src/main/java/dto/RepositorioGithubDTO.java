package dto;

import java.io.Serializable;

public class RepositorioGithubDTO implements Serializable {
	
	private static final long serialVersionUID = -4788732501345933252L;
	
	private Long id;
	private String node_id;
	private String name;
	private OwnerRepositoryGithubDTO owner;
	private String html_url;
	private String url;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNode_id() {
		return node_id;
	}
	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OwnerRepositoryGithubDTO getOwner() {
		return owner;
	}
	public void setOwner(OwnerRepositoryGithubDTO owner) {
		this.owner = owner;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
