package org.trrusst.reviews.db.software;

import junit.framework.TestCase;
import org.junit.Test;
import org.trrusst.reviews.db.DBConstants;

public class DerbyDBPopulateTest extends TestCase{

	@Test
	public void test_DerbyDBUtil_Test() {
        assertTrue(
                DerbyDBUtil.loadAzDataSet(DBConstants.FILE_AZ_DB_SOFTWARE_TEST)
        );
	}

}
