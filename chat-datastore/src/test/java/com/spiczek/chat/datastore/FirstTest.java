package com.spiczek.chat.datastore;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.spiczek.chat.datastore.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import com.google.appengine.api.datastore.Query;

import static org.junit.Assert.assertEquals;

/**
 * Created by piotr on 2014-07-28.
 */
public class FirstTest {

//    private final LocalServiceTestHelper helper =
//            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

//    @Before
//    public void setUp() {
//        helper.setUp();
//    }
//
//    @After
//    public void tearDown() {
//        helper.tearDown();
//    }


    @Test
    public void testInsert1() {
//        DAO d = new DAO();
//        d.createUser();
//        assertEquals(2, 1+1);
    }

}
