package local.picpay.picpay_backend.dto;

import local.picpay.picpay_backend.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
