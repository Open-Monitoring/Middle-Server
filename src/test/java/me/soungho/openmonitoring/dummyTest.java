package me.soungho.openmonitoring;

import com.google.common.net.InetAddresses;
import com.google.common.net.InternetDomainName;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

@Slf4j
public class dummyTest {

    @Test
    public void hostValidationTest(){
        String hostname = "www.bluelotussoftware.com";

        boolean result = InternetDomainName.isValid(hostname);
        System.out.println(MessageFormat.format("Is Internet Domain Name: {0} valid? {1}", hostname, result));

        hostname = "bluelotussoftware.com";
        result = InternetDomainName.isValid(hostname);
        System.out.println(MessageFormat.format("Is Internet Domain Name: {0} valid? {1}", hostname, result));

        hostname = "bluelotussoftware";
        result = InternetDomainName.isValid(hostname);
        System.out.println(MessageFormat.format("Is Internet Domain Name: {0} valid? {1}", hostname, result));

        hostname = "256.0.0.0";
        result = InternetDomainName.isValid(hostname);
        System.out.println(MessageFormat.format("Is Internet Domain Name: {0} valid? {1}", hostname, result));

        result = InetAddresses.isInetAddress(hostname);
        System.out.println(MessageFormat.format("Is Internet Address: {0} valid? {1}", hostname, result));

        hostname = "255.255.255.0";
        result = InternetDomainName.isValid(hostname);
        System.out.println(MessageFormat.format("Is Internet Domain Name: {0} valid? {1}", hostname, result));

        result = InetAddresses.isInetAddress(hostname);
        System.out.println(MessageFormat.format("Is Internet Address: {0} valid? {1}", hostname, result));

        hostname = "_xyz.bluelotussoftware.com";
        result = InternetDomainName.isValid(hostname);
        System.out.println(MessageFormat.format("Is Internet Domain Name: {0} valid? {1}", hostname, result));

        hostname = "localhost";
        result = InternetDomainName.isValid(hostname);
        System.out.println(MessageFormat.format("Is Internet Domain Name: {0} valid? {1}", hostname, result));

        hostname = "localhost:8080";
        result = InternetDomainName.isValid(hostname);
        System.out.println(MessageFormat.format("Is Internet Domain Name: {0} valid? {1}", hostname, result));

        hostname = "http://localhost";
        result = InternetDomainName.isValid(hostname);
        System.out.println(MessageFormat.format("Is Internet Domain Name: {0} valid? {1}", hostname, result));
    }
}
