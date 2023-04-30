package com.monitoring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TrafficTestController {

    @GetMapping("/traffic/cpu-test")
    public String cpuTest(){
        log.info("cpu test");
        long value = 0;
        for(long i = 0; i < 100000000000L; i++){
            value++;
        }
        return "ok value = " + value;
    }

    @GetMapping("/traffic/jvm-test")
    public String jvmTest(){
        log.info("jvm-test");
        List<String> list = new ArrayList<>();
        for(int i = 0; i< 1000000; i++){
            list.add("cnt = " + i);
        }
        return "ok";
    }

    @Autowired
    DataSource dataSource;

    @GetMapping("/traffic/jdbc-test")
    public String jdbcTest() throws SQLException {
        log.info("jdbc-test");
        Connection conn = dataSource.getConnection();
        log.info("conn info = {}", conn);
        //conn.close();
        return "ok";
    }

    @GetMapping("/traffic/error-test")
    public String errorTest(){
        log.error("error...");
        return "error";
    }

}
