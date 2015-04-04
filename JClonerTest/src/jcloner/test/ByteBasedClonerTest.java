package jcloner.test;

import jcloner.ByteBasedCloner;
import jcloner.ClonerException;
import jcloner.ICloner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ByteBasedClonerTest {

	private static ICloner byteBasedCloner = null;
	private static SourceObject sourceObject = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		byteBasedCloner = new ByteBasedCloner();
		sourceObject = new SourceObject();
		sourceObject.addObjectToList(new SourceListObject("Object 1"));
		sourceObject.addObjectToList(new SourceListObject("Object 2"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testByteBasedCloner() {
		try {
			SourceObject newObject = (SourceObject) byteBasedCloner.clone(sourceObject);
			Assert.assertTrue(newObject != sourceObject);
			Assert.assertTrue(newObject.getList() != sourceObject.getList());
		} catch (ClonerException e) {
			e.printStackTrace();
		}
	}

}
