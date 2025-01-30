package com.example.event_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("error")
    private String error;

    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

    public AuthenticationResponse(String accessToken, String error) {
        setAccessToken(accessToken);
        setError(error);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class AuthenticationResponseBuilder {
        private String accessToken;
        private String error;

        public AuthenticationResponseBuilder accessToken(String accessToken, String error) {
            this.accessToken = accessToken;
            this.error = error;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(accessToken, error);
        }
    }
}
