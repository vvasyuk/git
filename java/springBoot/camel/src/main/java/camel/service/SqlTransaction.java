package camel.service;

import org.apache.camel.Exchange;
import org.apache.camel.component.sql.ResultSetIterator;
import org.apache.camel.component.sql.ResultSetIteratorCompletion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component("SqlTransaction")
public class SqlTransaction {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void executeTransaction(Exchange exchange) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = jdbcTemplate.getDataSource().getConnection();
            execCallable(con, "my_pack.set_v1", (String) exchange.getIn().getHeader("key"));
            ps=con.prepareStatement("select my_pack.get_v1 from dual");
            boolean isResultSet = ps.execute();
            if (isResultSet) {
                rs = ps.getResultSet();
                RowMapper rowMapper = new ColumnMapRowMapper();
                ResultSetIterator iterator = new ResultSetIterator(con, ps, rs, rowMapper);
                exchange.getOut().getHeaders().putAll(exchange.getIn().getHeaders());
                exchange.getOut().getAttachments().putAll(exchange.getIn().getAttachments());
                exchange.getOut().setBody(iterator);
                exchange.addOnCompletion(new ResultSetIteratorCompletion(iterator));
            }

        } catch (Exception var12) {
            JdbcUtils.closeConnection(con);
            JdbcUtils.closeStatement(ps);
            JdbcUtils.closeResultSet(rs);
            throw var12;
        }
    }

    private void execCallable(Connection con, String procedure, String param) throws SQLException {
        String sql = "call my_pack.set_v1(?)";
        CallableStatement cStmt = con.prepareCall(sql);
        cStmt.setString(1, param);
        cStmt.execute();
    }
}

//if need to call anonymous block and set in/out params
//https://docs.oracle.com/cd/E17952_01/connector-j-en/connector-j-usagenotes-statements-callable.html