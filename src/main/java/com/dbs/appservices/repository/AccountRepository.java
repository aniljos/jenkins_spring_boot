package com.dbs.appservices.repository;

import com.dbs.appservices.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findByName(String name);

    @Query("select a.balance from AccountEntity a where a.id=:id")
    double getBalanceById(@Param("id") Long id);

    @Modifying
    @Query("update AccountEntity  a set a.balance=a.balance + :amt where a.id=:id")
    int deposit(@Param("id") Long id, @Param("amt") double amt);

    @Modifying
    @Query("update AccountEntity as a set a.balance=a.balance - :amt where a.id=:id")
    int withdraw(@Param("id") Long id, @Param("amt") double amt);

}
