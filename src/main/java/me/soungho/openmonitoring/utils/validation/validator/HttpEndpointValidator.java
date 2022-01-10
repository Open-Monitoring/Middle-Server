package me.soungho.openmonitoring.utils.validation.validator;

import lombok.extern.slf4j.Slf4j;
import me.soungho.openmonitoring.utils.validation.annotation.HTTPEndpoint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.common.net.InetAddresses;
import com.google.common.net.InternetDomainName;

@Slf4j
public class HttpEndpointValidator implements ConstraintValidator<HTTPEndpoint,String> {

    /***
     *
     * @param target    엔드포인트인지 검증해야하는 대상
     * @param constraintValidatorContext
     * @return true이면 검증 성공 / 검증이 실패할 시 ConstraintViolationException throw
     */
    @Override
    public boolean isValid(String target, ConstraintValidatorContext constraintValidatorContext) {
        log.info("call method: HttpEndpointValidator.isValid()");
        String[] tmpStrs = target.split(":");
        if(tmpStrs.length == 1){
            log.info("valid 실패");
            return false;
        }
        String hostname = tmpStrs[0];
        String port = tmpStrs[1];
        if(isValidEndpoint(hostname,port)){
            log.info("valid 성공");
            return true;
        }
        log.info("valid 실패");
        return false;
    }

    /***
     * hostname+port가 올바른 endpoint면 true리턴
     * @param hostname
     * @param port
     * @return
     */
    private boolean isValidEndpoint(String hostname, String port){
        return (InternetDomainName.isValid(hostname) || InetAddresses.isInetAddress(hostname)) &&
                (Integer.parseInt(port) >=0 && Integer.parseInt(port) <=65535 );
    }
}
