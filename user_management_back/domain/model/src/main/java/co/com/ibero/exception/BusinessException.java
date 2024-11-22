package co.com.ibero.exception;


import co.com.ibero.exception.message.BusinessExceptionMessage;

public class BusinessException extends RuntimeException {

    public BusinessException(BusinessExceptionMessage businessExceptionMessage) {
        super(businessExceptionMessage.getMessage());
    }

}
