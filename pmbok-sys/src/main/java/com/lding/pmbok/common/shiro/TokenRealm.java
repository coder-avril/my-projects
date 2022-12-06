package com.lding.pmbok.common.shiro;

import com.lding.pmbok.common.cache.Caches;
import com.lding.pmbok.pojo.dto.SysUserDto;
import com.lding.pmbok.pojo.po.SysResource;
import com.lding.pmbok.pojo.po.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;
import java.util.List;

@Slf4j
public class TokenRealm extends AuthorizingRealm {
    public TokenRealm(TokenMatcher matcher) {
        super(matcher);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        log.debug("TokenRealm - supports - {}", token);
        return token instanceof Token;
    }

    /**
     * 当主体（subject）要进行权限\角色判断时，就会调用
     *
     * 开发者需要在这个方法中干啥？【一般】
     * 根据用户名查询用户的角色\权限信息
     * @param principals 身份，即主体的标识属性，如用户名、邮箱等
     * @return 用户的角色\权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 拿到当前登录用户的token
        String token = (String) principals.getPrimaryPrincipal();
        log.debug("TokenRealm - doGetAuthorizationInfo - {}", token);

        // 根据token查找用户的角色、权限
        SysUserDto user = Caches.getToken(token);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<SysRole> roles = user.getRoles();
        if (CollectionUtils.isEmpty(roles)) return info;

        // 添加角色
        for (SysRole role : roles) {
            info.addRole(role.getName());
        }

        List<SysResource> resources = user.getResources();
        if (CollectionUtils.isEmpty(resources)) return info;
        // 添加权限
        for (SysResource resource : resources) {
            info.addStringPermission(resource.getPermission());
        }
        return info;
    }

    /**
     * 当主体（subject）要进行认证时，就会调用
     *
     * 开发者需要在这个方法中干啥？【一般】
     * 根据用户名查询用户的具体信息（用户名、密码）
     *
     * @param token subject.login()登录时传进来的token
     * @return 用户名的具体信息（用户名、密码）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取token
        String tk = ((Token) token).getToken();
        // 一般情况下可以在这里添加认证相关的代码（这里简单打印 不做要求
        log.debug("TokenRealm - doGetAuthenticationInfo - {}", tk);
        // 认证成功
        return new SimpleAuthenticationInfo(tk, tk, getName());
    }
}
