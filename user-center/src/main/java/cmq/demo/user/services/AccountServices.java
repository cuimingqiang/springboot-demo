package cmq.demo.user.services;

import cmq.demo.user.entities.AccountEntity;
import cmq.demo.user.models.BaseResult;
import cmq.demo.user.models.RegisterParamBean;
import cmq.demo.user.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by cuimingqiang on 16/5/20.
 */
@Service
public class AccountServices {

    @Autowired
    AccountRepository repository;

    @Autowired
    RedisConnectionFactory connectionFactory;

    @Autowired
    StringRedisTemplate template;

    public BaseResult register(RegisterParamBean param){
        BaseResult result = new BaseResult();

        AccountEntity entity = repository.exist(param.phone,param.password);
        if(entity != null){
            result.code = 100;
            result.msg = "account is exist";
            return result;
        }
        entity = new AccountEntity();
        entity.setUsername(param.phone);
        entity.setPassword(param.password);
        entity.setDevice(param.device);

        AccountEntity accountEntity = repository.save(entity);
        result.data = accountEntity;
        return result;
    }

    public BaseResult login(RegisterParamBean param){
        BaseResult result = new BaseResult();
        AccountEntity entity = repository.exist(param.phone,param.password);

        if(entity != null){
            result.data = entity;
            entity.setToken(UUID.randomUUID().toString());
            String value = entity.getToken();
            String key = "token_"+entity.getUuid();
            template.setConnectionFactory(connectionFactory);
            template.opsForValue().set(key,value);
            return result;
        }
        result.code = 101;
        result.msg = "account not exist or password error";
        return result;
    }

    public BaseResult login(String uuid,String token){
        BaseResult result = new BaseResult();
        template.setConnectionFactory(connectionFactory);
        String key = "token_"+uuid;
        String cacheToken = template.opsForValue().get(key);
        if(token.equals(cacheToken)){
            AccountEntity entity = repository.findOne(Long.decode(uuid));
            if(entity != null){
                entity.setToken(token);
                result.data = entity;
                return result;
            }
        }
        result.code = 102;
        result.msg = "token is invalid";
        return result;
    }
}
