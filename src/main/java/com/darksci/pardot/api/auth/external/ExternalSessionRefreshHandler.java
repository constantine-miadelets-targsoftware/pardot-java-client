package com.darksci.pardot.api.auth.external;

import com.darksci.pardot.api.auth.SessionRefreshHandler;

public class ExternalSessionRefreshHandler implements SessionRefreshHandler {

  private final ExternalTokenSource externalTokenSource;

  public ExternalSessionRefreshHandler(ExternalTokenSource externalTokenSource) {
    this.externalTokenSource = externalTokenSource;
  }

  @Override
  public boolean isValid() {
    return externalTokenSource.isValid();
  }

  @Override
  public void clearToken() {
    externalTokenSource.clearCurrentAccessToken();
  }

  @Override
  public boolean refreshCredentials() {
    return externalTokenSource.refreshAccessToken();
  }
}
