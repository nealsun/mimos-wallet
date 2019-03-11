package base.enums;

import lombok.Getter;

/**
 * @description:
 * @auther: dingyp
 * @date: 2019/1/18 4:17 PM
 */
public enum ResponseStatus {

    SUCCESS(1,"Success"),
    FAILED(-1,"Success");

    @Getter
    int code;

    @Getter
    String message;

    ResponseStatus(int codel, String message) {
        this.code = codel;
        this.message = message;
    }
}
