package conexao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoSql {

    private JdbcTemplate conexaosql;

    public ConexaoSql(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");



        dataSource.setUrl("jdbc:sqlserver://EC2" +
                "database=lifeline;" +
                "user=root;" +
                "password=root;" +
                "trustServerCertificate=true;");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");



        conexaosql = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaosql() {
        return conexaosql;
    }
}