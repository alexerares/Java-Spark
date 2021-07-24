package jdbc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ActiveRecordEntity {
    String tablename();
    String keyColumnName();
}
