package com.example.mysqlcheck.pojo;

import lombok.*;

import java.util.Objects;

/**
 * @author : WuMingrui
 * @date : 2022/8/11 14:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column {
    String columnName;
    String columnType;
    boolean isPrimaryKey;
    boolean isNullable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return isPrimaryKey == column.isPrimaryKey && isNullable == column.isNullable && Objects.equals(columnName, column.columnName) && Objects.equals(columnType, column.columnType);
    }

}
