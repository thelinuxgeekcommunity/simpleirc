package tk.jordynsmediagroup.simpleirc.model;

/**
 * Authentication credentials for a server.
 *
 */
public class Authentication {
  private String saslUsername;
  private String saslPassword;
  private String nickservPassword;

  /**
   * Does this instance have credentials for Nickserv authentication?
   *
   * @return True if nickserv credentials are present, false otherwise.
   */
  public boolean hasNickservCredentials() {
    return nickservPassword != null && nickservPassword.length() > 0;
  }

  /**
   * Does this instance have credentials for SASL authentication?
   *
   * @return True if nickserv credentials are present, false otherwise.
   */
  public boolean hasSaslCredentials() {
    return saslUsername != null && saslUsername.length() > 0;
  }

  /**
   * Set the username for SASL authentication.
   *
   * @param saslUsername
   */
  public void setSaslUsername(String saslUsername) {
    if( saslUsername == "" ) {
      saslUsername = null;
    }

    this.saslUsername = saslUsername;
  }

  /**
   * Set the password for SASL authentication.
   *
   * @param saslPassword
   */
  public void setSaslPassword(String saslPassword) {
    if( saslPassword == "" ) {
      saslPassword = null;
    }

    this.saslPassword = saslPassword;
  }

  /**
   * Set the password for Nickserv authentication.
   *
   * @param nickservPassword
   */
  public void setNickservPassword(String nickservPassword) {
    if( nickservPassword == "" ) {
      nickservPassword = null;
    }

    this.nickservPassword = nickservPassword;
  }

  /**
   * Get the username for SASL authentication.
   *
   * @return
   */
  public String getSaslUsername() {
    return saslUsername;
  }

  /**
   * Get the password for SASL authentication.
   *
   * @return
   */
  public String getSaslPassword() {
    return saslPassword;
  }

  /**
   * Get the password for Nickserv authentication.
   *
   * @return
   */
  public String getNickservPassword() {
    return nickservPassword;
  }
}
