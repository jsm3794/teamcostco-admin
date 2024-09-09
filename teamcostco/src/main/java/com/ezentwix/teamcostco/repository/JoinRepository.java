package com.ezentwix.teamcostco.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.ezentwix.teamcostco.dto.join.JoinDTO;
import lombok.RequiredArgsConstructor;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class JoinRepository {

    private final SqlSessionTemplate sql;

    public JoinDTO add(JoinDTO joinDTO) {
        sql.insert("Join.add", joinDTO);
        return joinDTO;
    }

    public boolean countId(String id) {
        int count = sql.selectOne("Join.count_id", id);
        return count == 0;
    }

    public void updateEmailVerificationToken(String loginId, String token) {
        sql.update("Join.updateEmailVerificationToken", Map.of("loginId", loginId, "token", token));
    }

    public boolean verifyEmail(String token) {
        int updatedRows = sql.update("Join.verifyEmail", token);
        return updatedRows > 0;
    }
}
