package com.example.tobyexam.dao;

import com.example.tobyexam.model.User;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public abstract class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:8432/postgres", "age", "age");

        conn = getConnection();

        String insertQuery = """                   
                INSERT INTO toby_exam.users
                (id, name, password)
                VALUES('%s', '%s', '%s');
                                
                """.formatted(user.getId(), user.getName(), user.getPassword());

        PreparedStatement ps = conn.prepareStatement(insertQuery);

/*        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());*/

        ps.executeUpdate();

        ps.close();
        conn.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        User user = null;
        String selectQuery = """
            SELECT id, name, password
            FROM toby_exam.users
            where id = '%s';              
                """.formatted(id);

        PreparedStatement ps = conn.prepareStatement(selectQuery);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        return user;
    }

    public void deleteById(String id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();

        String deleteQuery = """
            DELETE FROM toby_exam.users
            where id = '%s';              
                """.formatted(id);

        PreparedStatement ps = conn.prepareStatement(deleteQuery);
        ps.executeUpdate();

    }

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

    public Connection getConnection2() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:8432/postgres", "age", "age");
        return conn;
    }

    public void testGraphizer() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:8432/postgres", "age", "age");

        String insertQuery = """
            WITH sv AS (
            SELECT nsv.nspname as graph, tbl.relname as label, tbl.oid as oid
            FROM pg_namespace nsv
            JOIN pg_class tbl ON nsv.oid = tbl.relnamespace
            WHERE nsv.nspname = 'homogeneous_2'
            AND tbl.relname = 'member'
            ), svp AS (
            SELECT li.target_label_name, li.target_label_oid, pi.property_id, si.source_id
            FROM graphizer.tb_label_info li, graphizer.tb_property_info pi, sv, graphizer.tb_source_info si
            WHERE li.label_id = pi.label_id
            AND pi.source_id = si.source_id
            AND li.graph_name = sv.graph
            AND li.target_label_name = sv.label
            AND si.column_id = 1650
            ), ev AS (
            SELECT nsv.nspname as graph, tbl.relname as label, tbl.oid as oid
            FROM pg_namespace nsv
            JOIN pg_class tbl ON nsv.oid = tbl.relnamespace
            WHERE nsv.nspname = 'homogeneous_2'
            AND tbl.relname = 'member'
            ), evp AS (
            SELECT li.target_label_name, li.target_label_oid, pi.property_id, si.source_id
            FROM graphizer.tb_label_info li, graphizer.tb_property_info pi, ev, graphizer.tb_source_info si
            WHERE li.label_id = pi.label_id
            AND pi.source_id = si.source_id
            AND li.graph_name = ev.graph
            AND li.target_label_name = ev.label
            AND si.column_id = 1653
            )
            INSERT INTO graphizer.TB_LABEL_INFO
            (label_id, meta_id, graph_id, user_id, label_type, graph_name, start_label_name, start_label_oid, start_label_id, end_label_name, end_label_oid, end_label_id, target_label_name, target_label_oid, use_yn)
            SELECT nextval('graphizer.label_id_seq'), 927, 2, 'bitnine1', 'E', nsp.nspname, sv.label, sv.oid, svp.property_id, ev.label, ev.oid, evp.property_id, tbl.relname, tbl.oid, 'Y'
            FROM pg_namespace nsp, pg_class tbl, sv, svp, ev, evp
            WHERE nsp.oid = tbl.relnamespace
            AND nsp.nspname = 'homogeneous_2'
            AND tbl.relname = 'relation'
            AND svp.source_id = 2401
            AND evp.source_id = 2402;
                """;

        PreparedStatement ps = conn.prepareStatement(insertQuery);

/*        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());*/

        ps.executeUpdate();

        ps.close();
        conn.close();

    }


}
