package com.example.mysqlcheck;

import com.example.mysqlcheck.mapper.show.ShowDbInfoMapper;
import com.example.mysqlcheck.mapper.test.TestDbInfoMapper;
import com.example.mysqlcheck.pojo.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
class MySqlCheckApplicationTests {
    @Autowired
    private ShowDbInfoMapper showDbInfoMapper;
    @Autowired
    private TestDbInfoMapper testDbInfoMapper;

    @Value("${myconfig.dbName}")
    String dbName;


    @Test
    void contextLoads() {
//        List<String> smartTraderShow = showDbnInfoMapper.getTablesByName("smarttrader");
//        List<String> smartTraderTest = testDbInfoMapper.getTablesByName("smarttrader");
//        System.out.println(smartTraderShow);
//        System.out.println(smartTraderShow.size());
//        System.out.println(smartTraderTest);
//        System.out.println(smartTraderTest.size());
//        List<Column> columns = showDbnInfoMapper.getColumns("smarttrader", "t_account_detail");
//        System.out.println(columns);
        System.out.println(compareTwoDb());
    }


    public boolean compareTwoDb() {
        // 查看表是否存在
        List<String> smartTraderShow = showDbInfoMapper.getTablesByName(dbName);
        List<String> smartTraderTest = testDbInfoMapper.getTablesByName(dbName);
        boolean flag = true;
        flag = compareTables(smartTraderShow, smartTraderTest);
        return flag;
    }


    public boolean compareTables(List<String> smartTraderShow, List<String> smartTraderTest) {
        smartTraderShow.sort(String::compareTo);
        smartTraderTest.sort(String::compareTo);
        boolean flag = true;
        int showLen = smartTraderShow.size(), testLen = smartTraderTest.size();
        if (showLen != testLen) {
            flag = false;
        }
        int i = 0, j = 0;
        while(i < showLen && j < testLen) {
            String tableShow = smartTraderShow.get(i);
            String tableTest = smartTraderTest.get(j);
            int compare = tableShow.compareTo(tableTest);
            if (compare == 0) {
                if(!compareColumns(tableShow)) {
                    flag = false;
                    System.out.printf("%s 表结构不一致%n", tableShow);
                }
                i++;
                j++;
            } else if(compare < 0) {
                flag = false;
                System.out.printf("show: %s; test: null%n", tableShow);
                i++;
            } else {
                flag = false;
                System.out.printf("show: null; test: %s%n", tableTest);
                j++;
            }
        }
        while (i < showLen) {
            String tableShow = smartTraderShow.get(i);
            System.out.printf("show: %s; test: null%n", tableShow);
            i++;
        }
        while (j < testLen) {
            String tableTest = smartTraderTest.get(j);
            System.out.printf("show: null; test: %s%n", tableTest);
            j++;
        }
        return flag;
    }

    public boolean compareColumns(String tableName) {
        List<Column> columnsShow = showDbInfoMapper.getColumns(dbName, tableName);
        List<Column> columnsTest = testDbInfoMapper.getColumns(dbName, tableName);
        columnsShow.sort(Comparator.comparing(Column::getColumnName));
        columnsTest.sort(Comparator.comparing(Column::getColumnName));
        int showLen = columnsShow.size(), testLen = columnsTest.size();
        if (showLen != testLen) {
            return false;
        }
        int i = 0, j = 0;
        while(i < showLen && j < testLen) {
            Column columnShow = columnsShow.get(i);
            Column columnTest = columnsTest.get(j);
            int compare = columnShow.getColumnName().compareTo(columnTest.getColumnName());
            if (compare == 0) {
                if(columnShow.equals(columnTest)) {
                    i++;
                    j++;
                } else {
                    return false;
                }
            } else if(compare < 0) {
                return false;
            } else {
                return false;
            }
        }
        return true;
    }


}
