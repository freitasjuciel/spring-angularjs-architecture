package br.edu.utfpr.md.architecture.model.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown=true)
public enum Role implements GrantedAuthority
{
	ADMIN, USER;

	@Override
	public String getAuthority() 
	{
		return this.name();
	}
}
