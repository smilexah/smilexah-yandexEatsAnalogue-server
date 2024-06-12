package sdu.edu.kz.YandexEatsAnalogue.utils.constants;

import lombok.Getter;

@Getter
public enum Roles {
    CUSTOMER("ROLE_CUSTOMER"),ADMIN("ROLE_ADMIN"),PARTNER("ROLE_PARTNER");

    private final String role;
    private Roles(String role) {
        this.role = role;
    }
}
