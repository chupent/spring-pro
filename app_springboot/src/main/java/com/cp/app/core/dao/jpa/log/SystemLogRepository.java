package com.cp.app.core.dao.jpa.log;

import com.cp.app.core.model.bean.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemLogRepository
 * @Description TODO
 * @createdate 2019/7/22 星期一 17:49
 */
public interface SystemLogRepository extends JpaRepository<SysLog,Long> {
    Page<SysLog> findAll(Pageable pageable);
}
