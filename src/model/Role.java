package model;

public enum Role {
    ADMIN("ADMIN"),USER("USER");

    private String value;


    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
    public static Role fromValue(String value) {
        Role[] var1 = values();
        int var2 = var1.length;

        for (int i = 0; i < var2; ++i) {
            Role c = var1[i];
            if (c.value.equals(value)) {
                return c;
            }
        }

        throw new IllegalArgumentException("Invalid role value: " + value);
    }
}
