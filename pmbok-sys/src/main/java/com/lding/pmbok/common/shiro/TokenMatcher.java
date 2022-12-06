package com.lding.pmbok.common.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

@Slf4j
public class TokenMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        log.debug("TokenMatcher - doCredentialsMatch - {} {}", token, info);
        // 用于认证判断（一般用来比较用户和密码是否正确之类的，这里不做要求直接视为OK
        return true;
    }
}
