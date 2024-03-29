package work.chinadream.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import work.chinadream.entity.admin.ShiroUser;
import work.chinadream.entity.admin.User;
import work.chinadream.service.admin.IRoleService;
import work.chinadream.service.admin.IUserService;

import java.text.SimpleDateFormat;
import java.util.*;

/**          shiro 登录授权认证
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */

public class PersonRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  ---shiro 封装令牌---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = (String) token.getPrincipal();
        // 通过登录名查找用户
        User user = userService.findUserByLoginName(username);
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException();
        }
        // 账号未启用
        if (user.getStatus() == 1) {
            throw new DisabledAccountException();
        }
        //设置上次登录时间
        User u= new User();
        u.setId(user.getId());
        u.setLastlogintime(new Date());
        userService.updateLastLoginTime(u);

        // 根据用户id 查询出它所对应的角色id , 在 userRole 表中查询
        List<Long> roleList = roleService.findRoleIdListByUserId(user.getId());
        // 将数据放到 ShiroUser(VO) 中。后面将信息存进shiro中
        //转化日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss") ;
        String lastlogin = format.format(user.getLastlogintime());

        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginname(), user.getName(), roleList,lastlogin);
        //加盐
        ByteSource salt = ByteSource.Util.bytes(user.getLoginname());
        // 最后一步交给 shiro
        // 认证缓存信息
        SimpleAuthenticationInfo getInfo =
                new SimpleAuthenticationInfo(shiroUser,user.getPassword(),salt,getName());
        return getInfo;
    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        //  得到用户身份信息
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        //  得到用户角色 id
        List<Long> roleList = shiroUser.roleList;
        Set<String> urlSet = new HashSet<String>() ;
        for (Long roleId : roleList) {
            List<Map<Long, String>> roleResourceList = roleService.findRoleResourceListByRoleId(roleId);
            if (roleResourceList != null) {
                for (Map<Long, String> map : roleResourceList) {
                    if (StringUtils.isNoneBlank(map.get("permission"))) {
                        urlSet.add(map.get("permission"));
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urlSet);
        return info;
    }
    //清除当前用户权限信息
    public void clearCached() {
        //获取当前等的用户凭证，然后清除
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
