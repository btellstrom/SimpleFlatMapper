package org.sfm.csv;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Test;
import org.sfm.beans.DbFinalObject;
import org.sfm.beans.DbObject;
import org.sfm.beans.DbPartialFinalObject;
import org.sfm.jdbc.DbHelper;
import org.sfm.utils.ListHandler;

public class DynamicCsvMapperImplTest {

	private static final String CSV = "id,name,email,creationTime,typeOrdinal,typeName\n"
			+ "1,name 1,name1@mail.com,2014-03-04 11:10:03,2,type4";
	@Test
	public void testDbObject() throws Exception {
		
		CsvMapper<DbObject> mapper = CsvMapperFactory.newInstance().newMapper(DbObject.class);
		
		
		List<DbObject> list = mapper.forEach(dbObjectCsvStream(), new ListHandler<DbObject>()).getList();
		assertEquals(1, list.size());
		DbHelper.assertDbObjectMapping(list.get(0));

		list = mapper.forEach(dbObjectCsvReader(), new ListHandler<DbObject>()).getList();
		assertEquals(1, list.size());
		DbHelper.assertDbObjectMapping(list.get(0));

	}

	public static InputStream dbObjectCsvStream() throws UnsupportedEncodingException {
		InputStream sr = new ByteArrayInputStream(CSV.getBytes("UTF-8"));
		return sr;
	}
	
	public static Reader dbObjectCsvReader() throws UnsupportedEncodingException {
		Reader sr = new StringReader(CSV);
		return sr;
	}
	
	@Test
	public void testFinalDbObject() throws Exception {
		CsvMapper<DbFinalObject> mapper = CsvMapperFactory.newInstance().newMapper(DbFinalObject.class);

		List<DbFinalObject> list = mapper.forEach(dbObjectCsvStream(), new ListHandler<DbFinalObject>()).getList();
		assertEquals(1, list.size());
		DbHelper.assertDbObjectMapping(list.get(0));
		
		list = mapper.forEach(dbObjectCsvReader(), new ListHandler<DbFinalObject>()).getList();
		assertEquals(1, list.size());
		DbHelper.assertDbObjectMapping(list.get(0));
		
	}
	
	@Test
	public void testPartialFinalDbObject() throws Exception {
		CsvMapper<DbPartialFinalObject> mapper = CsvMapperFactory.newInstance().newMapper(DbPartialFinalObject.class);
		
		List<DbPartialFinalObject> list = mapper.forEach(dbObjectCsvStream(), new ListHandler<DbPartialFinalObject>()).getList();
		assertEquals(1, list.size());
		DbHelper.assertDbObjectMapping(list.get(0));
		
		list = mapper.forEach(dbObjectCsvReader(), new ListHandler<DbPartialFinalObject>()).getList();
		assertEquals(1, list.size());
		DbHelper.assertDbObjectMapping(list.get(0));
	}
}
