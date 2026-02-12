package com.saasveterinaria.auth;

import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Locale;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base32;
import org.springframework.stereotype.Service;

@Service
public class TotpService {
  private static final int SECRET_BYTES = 20;
  private static final int DIGITS = 6;
  private static final int PERIOD_SECONDS = 30;
  private static final int WINDOW = 1;
  private static final Base32 BASE32 = new Base32();
  private final SecureRandom secureRandom = new SecureRandom();

  public String generateSecret() {
    byte[] buffer = new byte[SECRET_BYTES];
    secureRandom.nextBytes(buffer);
    return BASE32.encodeToString(buffer).replace("=", "");
  }

  public String buildOtpAuthUrl(String issuer, String accountName, String secret) {
    String label = urlEncode(issuer + ":" + accountName);
    String issuerEncoded = urlEncode(issuer);
    return String.format(Locale.ROOT,
        "otpauth://totp/%s?secret=%s&issuer=%s&algorithm=SHA1&digits=%d&period=%d",
        label, secret, issuerEncoded, DIGITS, PERIOD_SECONDS);
  }

  public boolean verifyCode(String secret, String code, Instant now) {
    if (secret == null || code == null) {
      return false;
    }
    String normalized = code.trim();
    if (!normalized.matches("\\\\d{6}")) {
      return false;
    }
    long timeStep = now.getEpochSecond() / PERIOD_SECONDS;
    for (int i = -WINDOW; i <= WINDOW; i++) {
      String candidate = generateCode(secret, timeStep + i);
      if (normalized.equals(candidate)) {
        return true;
      }
    }
    return false;
  }

  public String generateCurrentCode(String secret, Instant now) {
    long timeStep = now.getEpochSecond() / PERIOD_SECONDS;
    return generateCode(secret, timeStep);
  }

  private String generateCode(String secret, long timeStep) {
    byte[] key = BASE32.decode(secret);
    byte[] data = ByteBuffer.allocate(8).putLong(timeStep).array();
    byte[] hash;
    try {
      Mac mac = Mac.getInstance("HmacSHA1");
      mac.init(new SecretKeySpec(key, "HmacSHA1"));
      hash = mac.doFinal(data);
    } catch (Exception ex) {
      throw new IllegalStateException("Unable to generate TOTP code", ex);
    }
    int offset = hash[hash.length - 1] & 0x0F;
    int binary =
        ((hash[offset] & 0x7F) << 24)
            | ((hash[offset + 1] & 0xFF) << 16)
            | ((hash[offset + 2] & 0xFF) << 8)
            | (hash[offset + 3] & 0xFF);
    int otp = binary % (int) Math.pow(10, DIGITS);
    return String.format(Locale.ROOT, "%0" + DIGITS + "d", otp);
  }

  private String urlEncode(String value) {
    return URLEncoder.encode(value, StandardCharsets.UTF_8);
  }
}
