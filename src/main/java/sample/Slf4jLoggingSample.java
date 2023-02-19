package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLoggingSample {
    private static final Logger logger = LoggerFactory.getLogger(Slf4jLoggingSample.class);
    public static void main(String[] args) {
        int newT = 15;
        int oldT = 16;
        Exception e = new IllegalArgumentException();

        // 1. using traditional API
        logger.debug("Temperature set to {}. Old value was {}.", newT, oldT);

        // 2. using fluent API, log message with arguments
        logger
          .atDebug()
          .log("Temperature set to {}. Old value was {}.", newT, oldT);

        // 3. using fluent API, add arguments one by one and then log message
        logger
          .atDebug()
          .setMessage("Temperature set to {}. Old value was {}.")
          .addArgument(newT)
          .addArgument(oldT)
          .log();

        // 4.
        // using fluent API, add one argument with a Supplier and then log message with one more argument.
        // Assume the method t16() returns 16.
        logger
            .atDebug()
            .setMessage("Temperature set to {}. Old value was {}.")
            .addArgument(() -> t16())
            .addArgument(oldT)
            .log();

        // 5. using fluent API
        logger
            .atDebug()
            .setMessage("Temperature changed.")
            .addKeyValue("oldT", oldT)
            .addKeyValue("newT", newT)
            .log();

        // -----------------------------
        // 公式から追加パターン
        // -----------------------------
        logger
            .atDebug()
            .setMessage("Temperature set to {}. Old value was {}.")
            .addArgument(t16())
            .addArgument(oldT)
            .log();

        // 正しい呼び出し
        logger.warn("会社名は{}です。", "mamezou", e);

        // 誤った呼び出し
        logger.warn("会社名は{}です。", e, "mamezou");

        // fluent APIを使った呼び出し
        logger
            .atWarn()
            .setMessage("会社名は{}です。")
            .setCause(e)
            .addArgument("mamezou")
            .log();


        String oldStyle = String.format("会社名は%sです", "mamezou");
        String newStyle = "会社名は%sです".formatted("mamezou");
        System.out.println(oldStyle);
        System.out.println(newStyle);
    }

    static int t16() {
        return 16;
    }
}
