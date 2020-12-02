package persistence;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StaffDAOTest {

    StaffDAO staffDAO = null;

    @BeforeTest
    public void beforeTest(){
        System.out.println("Run Before Test");
        staffDAO = new StaffDAO();
    }

    @AfterTest
    public void afterTest(){
        System.out.println("Run after Test");

        staffDAO = null;
    }
    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }


    @Test(priority = 1)
    public void testValidateUserTrue() {
        String name = "admin";
        System.out.println("Check valid user");
        Assert.assertEquals(true, staffDAO.checkIfExists(name));
    }
    @Test(priority = 3)
    public void testValidateUserfalse() {
        String name = "hacker";
        System.out.println("Invalid user");
        Assert.assertEquals(false , staffDAO.checkIfExists(name));
    }
    @Test(priority = 2)
    public void testValidateUser() {

        System.out.println("Check sql injection using OR 1=1");
        Assert.assertEquals(true, staffDAO.checkIfExists("hacker\'or \"\"=\'"));
    }
    @Test(enabled = false)
    public void testGetRole() {
    }

    @Test(enabled = false)
    public void testCheckIfExists() {
    }

    @Test
    public void testAddUser() {
    }

    @Test
    public void testDeleteUser() {
    }

    @Test
    public void testGetAllStaff() {
    }

    @Test
    public void testUpdateLockedData() {
    }


}