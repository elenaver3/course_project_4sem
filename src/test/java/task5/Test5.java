package task5;

import common.BaseTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class Test5 extends BaseTest {

    @BeforeClass
    public static void setUp() {
        BaseTest.setUp();
        requestSpecification.basePath("/users");
    }

    @Test
    public void test5Test1() {

        UsersList usersList = checkStatusCodeGet("?page=2", 200)
                .extract().as(UsersList.class);
        Assert.assertEquals(usersList.getPage(), 2);
        Assert.assertEquals(usersList.getPerPage(), 6);
        Assert.assertEquals(usersList.getTotal(), 12);
        Assert.assertEquals(usersList.getTotalPages(), 2);
        Assert.assertFalse(usersList.getData().isEmpty());
        Assert.assertEquals(usersList.getData().get(2).getFirstName(), "Tobias");
        Assert.assertEquals(usersList.getData().get(2).getLastName(), "Funke");

    }

//    @Test
//    public void test5Test2() {
//
//        UsersList usersList = checkStatusCodeGet("2", 200)
//                .extract().as(UsersList.class);
//        Assert.assertEquals(usersList.getData().get(2).getId(), "2");
//
//    }

    @Test
    public void test5Test3() {

//        UsersList usersList = checkStatusCodeGet("23", 404)
//                .extract().as(UsersList.class);
//        Assert.assertTrue(usersList.getData().is);

    }

    @Test
    public void test5Test4() {

        UsersList usersList = checkStatusCodeGet("", 200)
                .extract().as(UsersList.class);
        Assert.assertEquals(usersList.getPage(), 2);
        Assert.assertEquals(usersList.getPerPage(), 6);
        Assert.assertEquals(usersList.getTotal(), 12);
        Assert.assertEquals(usersList.getTotalPages(), 2);
        Assert.assertFalse(usersList.getData().isEmpty());
        Assert.assertEquals(usersList.getData().get(2).getFirstName(), "Tobias");
        Assert.assertEquals(usersList.getData().get(2).getLastName(), "Funke");

    }

}
