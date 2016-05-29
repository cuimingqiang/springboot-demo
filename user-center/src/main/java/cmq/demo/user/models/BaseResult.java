package cmq.demo.user.models;

import org.springframework.http.HttpStatus;

/**
 * Created by cuimingqiang on 16/5/18.
 */
public class BaseResult <T>{
    public int code = HttpStatus.OK.value();
    public String msg = HttpStatus.OK.name();
    public T data;
}
