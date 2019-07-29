package com.cp.app.core.service.user;

import com.cp.app.core.comm.api.UserApi;
import com.cp.app.core.dao.jpa.user.SysUserRepository;
import com.cp.app.core.model.bean.SysUser;
import com.cp.app.core.model.pojo.ApiResponse;
import com.cp.app.core.comm.basics.BasicsService;
import com.cp.app.core.model.pojo.params.PageOut;
import com.cp.app.core.model.pojo.params.user.UserIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserService
 * @Description TODO
 * @createdate 2019/4/3 星期三 10:58
 */
@Service
public class UserService extends BasicsService implements UserApi {
    @Autowired
    private SysUserRepository repository;

    public ApiResponse<SysUser> getUserInfo(String username) {
        SysUser user =repository.findByAccount(username);
        return this.bindSucceedResult(user);
    }

    @Override
    public ApiResponse<PageOut<SysUser>> getUsers(UserIn param) {
        PageRequest request = new PageRequest(param.getPage(),param.getSize(),Sort.Direction.DESC,"userId");

        Specification specification= (Specification<SysUser>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            String username = param.getUsername();
            String account = param.getAccount();
            if(!StringUtils.isEmpty(username)){
                predicates.add(criteriaBuilder.like(root.get("userName").as(String.class), "%" + username+ "%"));
            }
            if(!StringUtils.isEmpty(account)){
                predicates.add(criteriaBuilder.like(root.get("account").as(String.class), "%" + account+ "%"));
            }

            Predicate[] pre = new Predicate[predicates.size()];
            pre = predicates.toArray(pre);
            query.where(pre);

            Predicate predicate = criteriaBuilder.and(pre);
            return predicate;
        };

        PageOut<SysUser> pageOut = new PageOut<SysUser>(repository.findAll(specification,request));
        return this.bindSucceedResult(pageOut);
    }
}
