package config;

import java.util.Locale;

public final class TestConfig {

    private TestConfig() {}

    public static final String ENV = System.getenv()
            .getOrDefault("ENV", "qa")
            .trim()
            .toLowerCase(Locale.ROOT);

    public static final String BASE_URL = switch (ENV) {
        case "qa" -> "https://the-internet.herokuapp.com";
        case "uat" -> "https://the-internet.herokuapp.com";
        default -> throw new IllegalArgumentException(
                "Unknown environment: " + ENV
                        + ". Supported environments: qa, uat"
        );
    };

    public static final boolean HEADLESS = Boolean.parseBoolean(
            System.getenv().getOrDefault("HEADLESS", "false")
    );
}