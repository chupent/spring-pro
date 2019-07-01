package com.cp.app.core.dao.jpa;

import com.cp.app.core.model.bean.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SysResourceRepository
 * @Description TODO
 * @createdate 2019/2/21 星期四 11:42
 */
public interface SysResourceRepository extends JpaRepository<SysResource, Long> {
    SysResource findByResUrl(String resUrl);
}
