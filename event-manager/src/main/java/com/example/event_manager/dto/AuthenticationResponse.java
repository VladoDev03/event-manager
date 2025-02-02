package com.example.event_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("error")
    private String error;

    @JsonProperty("userId")
    private long userId;

    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

    public AuthenticationResponse(String accessToken, String error, long userId) {
        setAccessToken(accessToken);
        setError(error);
        setUserId(userId);
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public static class AuthenticationResponseBuilder {
        private String accessToken;
        private String error;
        private long userId;

        public AuthenticationResponseBuilder accessToken(String accessToken, String error, long userId) {
            this.accessToken = accessToken;
            this.error = error;
            this.userId = userId;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(accessToken, error, userId);
        }
    }
}