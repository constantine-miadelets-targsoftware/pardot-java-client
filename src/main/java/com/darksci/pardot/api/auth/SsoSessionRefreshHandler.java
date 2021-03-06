/**
 * Copyright 2017, 2018, 2019, 2020 Stephen Powis https://github.com/Crim/pardot-java-client
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.darksci.pardot.api.auth;

import com.darksci.pardot.api.PardotClient;
import com.darksci.pardot.api.config.SsoLoginCredentials;
import com.darksci.pardot.api.request.login.SsoLoginRequest;
import com.darksci.pardot.api.response.login.SsoLoginResponse;

import java.util.Objects;

/**
 * Handles refreshing credentials using SSO Login method.
 */
public class SsoSessionRefreshHandler implements SessionRefreshHandler {
    private final SsoLoginCredentials credentials;
    private final PardotClient client;

    public SsoSessionRefreshHandler(final SsoLoginCredentials credentials, final PardotClient client) {
        this.credentials = Objects.requireNonNull(credentials);
        this.client = Objects.requireNonNull(client);
    }

    @Override
    public boolean isValid() {
        return credentials.hasAccessToken();
    }

    @Override
    public void clearToken() {
        credentials.clearAccessToken();
    }

    @Override
    public boolean refreshCredentials() {
        final SsoLoginResponse response = client.login(new SsoLoginRequest()
            .withClientId(credentials.getClientId())
            .withClientSecret(credentials.getClientSecret())
            .withUsername(credentials.getUsername())
            .withPassword(credentials.getPassword())
        );

        // If we have an API key.
        if (response.getAccessToken() != null) {
            // Set it.
            credentials.setAccessToken(response.getAccessToken());
            return true;
        }

        return false;
    }
}
