package com.cp.app.core.dao.jpa;

import com.cp.app.core.model.bean.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SysResourceRepository
 * @Description TODO
 * @createdate 2019/2/21 星期四 11:42
 */
public interface SysResourceRepository extends JpaRepository<SysResource, Long> {
    //    @Query(value = "select r.res_id,r.res_name,r.res_url from sys_resource r where r.res_url=?1",nativeQuery = true)
    SysResource findByResUrl(String resUrl);
}
