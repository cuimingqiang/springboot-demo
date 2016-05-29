package cmq.demo.user.models;

/**
 * Created by cuimingqiang on 16/5/20.
 */
public class RegisterParamBean {
    public String phone;
    public String password;
    public String device;

    @Override
    public String toString() {
        return "RegisterParamBean{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", device='" + device + '\'' +
                '}';
    }
}
