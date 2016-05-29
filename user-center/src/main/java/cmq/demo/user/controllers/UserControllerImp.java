package cmq.demo.user.controllers;

import cmq.demo.user.models.BaseResult;
import cmq.demo.user.models.RegisterParamBean;
import cmq.demo.user.services.AccountServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cuimingqiang on 16/5/18.
 */
@RestController
@RequestMapping(value = "/user",produces = {"application/json"})
public class UserControllerImp {
    private static Logger logger = Logger.getLogger(UserControllerImp.class);
    @Autowired
    AccountServices accountServices;

    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    public BaseResult register(@RequestBody RegisterParamBean param){
        return accountServices.register(param);
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public BaseResult login(@RequestBody RegisterParamBean param){
        logger.info(param);
        return accountServices.login(param);
    }

    @RequestMapping(value = "/login/{uuid}/{token}")
    public BaseResult login(@PathVariable("uuid")String uuid,@PathVariable("token")String token){
        logger.info(uuid+","+token);
        return accountServices.login(uuid,token);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserControllerImp.class, args);
    }
}
