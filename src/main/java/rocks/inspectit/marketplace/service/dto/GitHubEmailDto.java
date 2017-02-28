package rocks.inspectit.marketplace.service.dto;

/**
 * Simple DTO to get the user email addresses from uri: <a href="https://api.github.com/user/emails">https://api.github.com/user/emails?access_token=${token}</a>.
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.1.0-SNAPSHOT
 */
public class GitHubEmailDto {

	private String email;
	private String visibility;

	private boolean primary;
	private boolean verified;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
