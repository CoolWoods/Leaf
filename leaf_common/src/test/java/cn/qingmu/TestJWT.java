package cn.qingmu;

import cn.qingmu.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest(classes = CommonApplication.class)
public class TestJWT {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void testCreateJWT(){
        jwtUtil.setKey("811236");
        jwtUtil.setTtl(60*60);
        String jwt = jwtUtil.createJWT("123", "JWT", "user");
        System.out.println(jwt);
    }

    @Test
    void testParseJWT(){
        System.out.println(new Date());
        jwtUtil.setKey("811236");
        //设置过期时间为一天
        jwtUtil.setTtl(1000*60*60*24);
        String jwt = jwtUtil.createJWT("123", "JWT", "user");
        Claims claims = jwtUtil.parseJWT(jwt);
        System.out.println(claims);
        System.out.println(claims.getExpiration());
    }

    @Test
    void testParseJWT1(){
        jwtUtil.setKey("811236");
        jwtUtil.setTtl(60*60);
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTYiLCJzdWIiOiIxMzg3NzA4OTgyMCIsImlhdCI6MTU5OTEwNDkwMCwicm9sZXMiOiJ1c2VyIiwiZXhwIjoxNTk5MTA4NTAwfQ.T70QsQnWuMTYKiAIbqu9dg27Xv3DUsCNjWGnnuepyNQ";
        Claims claims = jwtUtil.parseJWT(jwt);
        System.out.println(claims);
        System.out.println(claims.getExpiration());
    }

}
