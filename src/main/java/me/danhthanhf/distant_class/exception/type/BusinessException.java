package me.danhthanhf.distant_class.exception.type;

import me.danhthanhf.distant_class.common.constant.ErrorConstants;

public class BusinessException extends BaseException {

    protected BusinessException(String message) {
        super(ErrorConstants.ERR_BUSINESS_LOGIC, message);
    }

    protected BusinessException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
