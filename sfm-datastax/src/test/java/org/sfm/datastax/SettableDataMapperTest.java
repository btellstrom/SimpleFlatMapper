package org.sfm.datastax;

import com.datastax.driver.core.*;
import org.junit.Test;
import org.sfm.beans.DbObject;
import org.sfm.map.Mapper;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class SettableDataMapperTest extends AbstractDatastaxTest {

    @Test
    public void testInsertDbObjects() throws Exception {
        testInSession(new Callback() {
            @Override
            public void call(Session session) throws Exception {

                PreparedStatement preparedStatement = session.prepare("insert into " +
                        "dbobjects(id, name, email, creation_time, type_ordinal, type_name) " +
                        "values(?, ?, ?, ?, ?, ?)");

                SettableDataMapperBuilder<DbObject> builder = SettableDataMapperBuilder.newBuilder(DbObject.class);

                ColumnDefinitions variables = preparedStatement.getVariables();

                for(int i = 0; i < variables.size(); i++) {
                    builder.addColumn(new DatastaxColumnKey(variables.getName(i), i, variables.getType(i)));
                }

                Mapper<DbObject, SettableByIndexData> mapper = builder.mapper();

                DbObject dbObject = new DbObject();
                dbObject.setId(2666l);
                dbObject.setCreationTime(new Date());
                dbObject.setEmail("a@a.a");
                dbObject.setName("ssss");
                dbObject.setTypeName(DbObject.Type.type1);
                dbObject.setTypeOrdinal(DbObject.Type.type2);
                BoundStatement bind = preparedStatement.bind();
                mapper.mapTo(dbObject, bind, null);

                session.execute(bind);

                DbObject actual = DatastaxMapperFactory.newInstance().mapTo(DbObject.class).iterator(session.execute("select * from dbobjects")).next();
                assertEquals(dbObject, actual);




            }
        });
    }

    @Override
    protected void tearDown(Session session) {
        session.execute("DELETE FROM dbobjects where id = 2666");
    }
}