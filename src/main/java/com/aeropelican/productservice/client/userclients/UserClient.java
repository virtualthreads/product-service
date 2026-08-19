package com.aeropelican.productservice.client.userclients;

import com.aeropelican.productservice.client.dto.ApiResponse;
import com.aeropelican.productservice.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name="user-service")
public interface UserClient {

    //GET {userId}
    @GetMapping("/api/v1/users/{userId}")
    ApiResponse<UserResponse> retrieveUserDetails(@PathVariable("userId") UUID id);

    @PostMapping("/api/v1/users/search")
    ApiResponse<UserResponse> searchProducts(@PathVariable("userId") UUID id);
}
