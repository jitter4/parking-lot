package com.example.parkinglot.beans.response.dto;

import com.example.parkinglot.beans.dto.AppUserDetails;
import lombok.Data;

@Data
public class AuthenticatedUserResponse {

    private String utid;
    private String jti;
    private String accessToken;
    private AppUserDetails userDetails;

    public AuthenticatedUserResponse(String utid, String jti, String accessToken, String name, String role) {
        this.utid = utid;
        this.jti = jti;
        this.accessToken = accessToken;
        this.userDetails = new AppUserDetails();
        this.userDetails.setRole(role);
        this.userDetails.setName(name);
    }

}
