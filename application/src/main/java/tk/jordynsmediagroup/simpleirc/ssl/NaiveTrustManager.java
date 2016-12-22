package tk.jordynsmediagroup.simpleirc.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Naive Trust Manager that accepts every certificate
 *
 */
public class NaiveTrustManager implements X509TrustManager {
  /**
   * Check client trusted
   *
   * @throws CertificateException if not trusted
   */
  @Override
  public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    // No Exception == Trust
  }

  /**
   * Check server trusted
   *
   * @throws CertificateException if not trusted
   */
  @Override
  public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    // No Exception == Trust
  }

  /**
   * Get accepted issuers
   */
  @Override
  public X509Certificate[] getAcceptedIssuers() {
    return new X509Certificate[0];
  }
}
