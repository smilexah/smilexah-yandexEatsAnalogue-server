package sdu.edu.kz.YandexEatsAnalogue.utils.constants;

import lombok.Getter;

@Getter
public enum Privileges {
    RESET_ANY_USER_PASSWORD(1l, "RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2l, "ACCESS_ADMIN_PANEL");

    private final Long id;
    private final String privilege;
    private Privileges(Long id, String privilege) {
        this.id = id;
        this.privilege = privilege;
    }
}
