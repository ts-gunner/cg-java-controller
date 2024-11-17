# cg-market

本项目原本是python项目，现在转成java项目，很多语法不太熟悉，本项目是为了熟悉java语法而生。
记录一下忘记的点

# project framework

cg-java-api是web项目



# Question Set

## Mybatis mapper中如何传入List和接收使用这个List

e.g.

```java

// UserPermissionMapper.class

public interface UserPermissionMapper extends BaseMapper<AuthPermission> {

    List<UserPermissionBase> getUserPermissionListByRoleId(List<String> roleIds);
}

```

```xml
<!-- UserPermissionMapper.xml -->
 <select id="getUserPermissionListByRoleId"  resultMap="UserPermissionMap">
        SELECT auth_permission.permission_id as permission_id, auth_permission.permission_name as permission_name
        FROM role_permission
        LEFT JOIN auth_permission ON role_permission.permission_id = auth_permission.permission_id
        WHERE role_permission.role_id IN (
        <foreach collection="roleIds" item="roleId" separator=",">
            #{roleId}
        </foreach>
        )

    </select>

```

## IDEA debug模式下 console无法输入代码，显示只读状态