package com.cp.app.core.web.user;

import com.cp.app.core.comm.api.UserApi;
import com.cp.app.core.comm.uitl.QRCodeUtil;
import com.cp.app.core.model.bean.SysUser;
import com.cp.app.core.model.pojo.ApiResponse;
import com.cp.app.core.comm.basics.BasicsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserController
 * @Description TODO
 * @createdate 2019/2/14 星期四 14:15
 */
@RestController
public class UserController extends BasicsController implements UserApi {

    @Autowired
    private UserApi userService;

    @GetMapping("/api/user/getUserInfo")
    public ApiResponse<SysUser> getUserInfo(String username) {
        return userService.getUserInfo(username);
    }

    @GetMapping("/api/user/getQRCode")
    public ApiResponse<String> getQRCode(HttpSession session,long time){
        Object obejct = session.getAttribute("time");
        if(null==obejct){
            session.setAttribute("time",time);
        }else {
           long n = (long) obejct;
           if( time - n <1000*60){
               return new ApiResponse(ApiResponse.FAIL_CODE,"请求频繁");
           }
        }
        String uuidcode = QRCodeUtil.generatorQRCodeString(UUID.randomUUID().toString(),"png",100,100);//生成二维码
        session.setAttribute("uuidcode",uuidcode);

        ApiResponse apiResponse = new ApiResponse(ApiResponse.OK_CODE,ApiResponse.OK_MSG);
        apiResponse.setResData(uuidcode);
        return apiResponse;
    }

    @GetMapping("/api/user/qRCodeLogin")
    public ApiResponse<String> qRCodeLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        String uuidcode = (String) session.getAttribute("uuidcode");
        if(StringUtils.isEmpty(uuidcode)){
            return new ApiResponse(ApiResponse.FAIL_CODE,"扫描登录失败!");
        }
        //登录认证，通过uuidcode获取登录校验
        return new ApiResponse(uuidcode);
    }
}
