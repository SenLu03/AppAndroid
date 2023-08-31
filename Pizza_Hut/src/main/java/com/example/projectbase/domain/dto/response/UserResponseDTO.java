package com.example.projectbase.domain.dto.response;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class UserResponseDTO {
    private Long id;
    private String fullName;
    private String accessToken;
    private String refreshToken;
    private Collection<? extends GrantedAuthority> authorities;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String fullName, String accessToken, String refreshToken, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.fullName = fullName;
        this.accessToken = accessToken;
        this.authorities = authorities;
        this.refreshToken = refreshToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
