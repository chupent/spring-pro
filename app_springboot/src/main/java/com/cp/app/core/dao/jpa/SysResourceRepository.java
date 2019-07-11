package com.cp.app.core.dao.jpa;

import com.cp.app.core.model.bean.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SysResourceRepository
 * @Description TODO
 * @createdate 2019/2/21 星期四 11:42
 */
public interface SysResourceRepository extends JpaRepository<SysResource, Long> {
    /**
     * 根据路径查询菜单
     * @param resUrl
     * @return
     */
    SysResource findByResUrl(String resUrl);
    /**
     * 根据用户查询菜单权限
     * @param userId
     * @return
     */
    @Query(value = "SELECT r2.* FROM sys_role_resource r1 , sys_resource r2 , sys_role r3 , sys_user_role r4 WHERE r1.res_id = r2.res_id AND r1.role_id = r3.role_id AND r4.role_id = r3.role_id AND r2.res_is_hef != 2 AND r4.user_id =:userId ORDER BY r2.res_prant",nativeQuery = true)
    List<SysResource> findByUserId(@Param("userId") Long userId);
}
