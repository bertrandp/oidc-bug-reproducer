package fr.sunr.sme.avscheduler.security;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.identity.SecurityIdentity;

import javax.json.JsonArray;
import javax.json.JsonValue;
import java.util.HashSet;
import java.util.Set;

public class User {

	/** identifiant. */
	private final String id;
	/** Nom d'utilisateur. */
	private final String userName;
	/** Roles. */
	private final Set<String> roles;
	/** Identifiants de site. */
	private final Set<Integer> locationIds = new HashSet<>();

	/** Constructeur. */
	public User(SecurityIdentity identity) {
		OidcJwtCallerPrincipal principal = (OidcJwtCallerPrincipal) identity.getPrincipal();

		this.id = principal.getClaim("sub");
		this.userName = principal.getName();
		this.roles = identity.getRoles();
		JsonArray jsonArray = principal.getClaim("locationIds");
		if (jsonArray != null) {
			for (JsonValue jsonValue : jsonArray) {
				this.locationIds.add(Integer.valueOf(jsonValue.toString()));
			}
		}
	}

	public String getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public Set<Integer> getLocationIds() {
		return locationIds;
	}

}