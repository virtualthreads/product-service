package com.aeropelican.productservice.mapper;

import com.aeropelican.productservice.dto.request.UserRequest;
import com.aeropelican.productservice.dto.response.UserResponse;
import com.aeropelican.productservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Converts UserRequest (Record) -> User (Entity)
    public User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(request.password());
        return user;
    }

    // Converts User (Entity) -> UserResponse (Record)
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        System.out.println("Before fetching product entity");
        System.out.println("Attempting to fetch product entity");

        return new UserResponse(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getStatus(),
                user.getCreateAt(),
                user.getUpdatedAt()
        );
    }
}