package com.darksci.pardot.api.auth.external;

public interface ExternalTokenSource {

  String getAccessToken();

  void clearCurrentAccessToken();

  boolean refreshAccessToken();

  boolean isValid();

  String getBusinessUnitId();
}
