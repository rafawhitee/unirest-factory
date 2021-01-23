package dto;

import java.io.Serializable;

public class OwnerRepositoryGithubDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String login;
	private Long id;
	private String node_id;
	private String avatar_url;
	private String gravatar_url;
	private String url;
	private String html_url;
	private String type;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
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
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public String getGravatar_url() {
		return gravatar_url;
	}
	public void setGravatar_url(String gravatar_url) {
		this.gravatar_url = gravatar_url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}