package de.siemering.mapping;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TextIdGenerator extends IdentityGenerator {

    private boolean initialCall = true;

    private static final String SEQ_NAME = "SEQ_TEXT";
    private static final String SELECT_SQL = "call NEXT VALUE FOR  " + SEQ_NAME;
    private static final String CREATE_SQL = "CREATE SEQUENCE " + SEQ_NAME + " start with 100 increment by 1";


    @Override
    public Serializable generate(SessionImplementor session, Object obj) throws HibernateException {

        if(initialCall){
            initialCall = false;
            try {
                session.connection().createStatement().execute(CREATE_SQL);
            } catch (SQLException e) {
                //
                e.printStackTrace();
            }
        }

        Text text = (Text) obj;
        if (text.id != null) return text.id;

        Connection connection = session.connection();
        try {

            PreparedStatement ps = connection.prepareStatement(SELECT_SQL);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long id = rs.getLong(1);
                return id;
            }else{
                throw new HibernateException("Unable to generate id from Sequenze SEQ_TEXT.");
            }

        } catch (SQLException e) {
            throw new HibernateException("Unable to generate Stock Code Sequence", e);
        }
    }
}