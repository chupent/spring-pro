package com.cp.app.core.dao.jpa.user;

import com.cp.app.core.model.bean.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SysRoleRepository
 * @Description TODO
 * @createdate 2019/2/21 星期四 10:07
 */
public interface SysRoleRepository extends JpaRepository<SysRole, Long>, JpaSpecificationExecutor<SysRole> {
    /*根据用户id查找用户的角色*/
    @Query(value = "select t1.role_id ,t1.role_name  from sys_role t1,sys_user_role t2 where t1.role_id=t2.role_id and t2.user_id =:userId", nativeQuery = true)
    List<SysRole> findSysRoleOfUserId(@Param("userId") long userId);

    /*根据资源查找对应的用户角色*/
    @Query(value = "select t1.role_id,t1.role_name from sys_role t1, sys_role_resource t2 where t1.role_id = t2.role_id and t2.res_id =:resId", nativeQuery = true)
    List<SysRole> findSysRoleOfResourceId(@Param("resId") long resId);
}
