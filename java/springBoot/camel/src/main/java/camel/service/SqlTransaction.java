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
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("SqlTransaction")
public class SqlTransaction {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void executeTransaction(Exchange exchange) throws SQLException {

        String pre = (String) exchange.getIn().getHeader("pre");
        String statement = (String) exchange.getIn().getHeader("statement");

        pre = prepareSql(pre,
                ":#(\\w+)'?",
                (s)->{return (String) exchange.getIn().getHeader(s);},
                "'", "'");

        pre = prepareSql(pre,
                "(sql:)'?",
                (s)->{return "";},
                "", "");

        System.out.println(pre);

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = jdbcTemplate.getDataSource().getConnection();
            execCallable(con, pre);
            ps=con.prepareStatement(statement);
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

    private void execCallable(Connection con, String sql) throws SQLException {
        CallableStatement cStmt = con.prepareCall(sql);
        cStmt.execute();
    }

    private String prepareSql(String input, String pattern, Function<String, String> f, String left, String right){
        StringBuffer newString = new StringBuffer();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);

        while (m.find()) {
            String id= m.group(1);
            //String newId = "'"+(String) ex.getIn().getHeader(id)+"'";
            String newId = left + f.apply(id) + right;
            m.appendReplacement(newString, newId);
        }
        m.appendTail(newString);

        return newString.toString();
    }
}

//if need to call anonymous block and set in/out params
//https://docs.oracle.com/cd/E17952_01/connector-j-en/connector-j-usagenotes-statements-callable.html
