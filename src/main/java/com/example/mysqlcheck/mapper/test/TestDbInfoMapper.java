package com.example.mysqlcheck.mapper.test;

import com.example.mysqlcheck.pojo.Column;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : WuMingrui
 * @date : 2022/8/11 14:51
 */
@Repository(value = "testDbInfoMapper")
public interface TestDbInfoMapper {
    List<String> getTablesByName(@Param("dbName") String dbName);

    List<Column> getColumns(@Param("dbName") String dbName, @Param("tableName") String tableName);
}
