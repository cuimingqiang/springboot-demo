package cmq.demo.user.repositories;

import cmq.demo.user.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by cuimingqiang on 16/5/20.
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long>{

    @Query("select a from #{#entityName} a where a.phone=:phone and a.password=:password")
    AccountEntity exist(@Param("phone") String phone,@Param("password") String password);
}
