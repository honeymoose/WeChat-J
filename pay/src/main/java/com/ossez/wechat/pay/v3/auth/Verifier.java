package com.ossez.wechat.pay.v3.auth;

import java.security.cert.X509Certificate;

public interface Verifier {
  boolean verify(String serialNumber, byte[] message, String signature);


  X509Certificate getValidCertificate();
}
