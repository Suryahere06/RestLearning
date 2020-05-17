package com.iPivot.RestLearning.RestLearning;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
 
public class Testgroups {
 
    @BeforeGroups("security")
    public void setUpSecurity() {
        System.out.println("setUpSecurity()");
    }
  
    @AfterGroups("security")
    public void tearDownSecurity() {
        System.out.println("tearDownSecurity()\n");
    }
 
    @BeforeGroups("database")
    public void setUpDatabase() {
        System.out.println("setUpDatabase()");
    }
  
    @AfterGroups("database")
    public void tearDownDatabase() {
        System.out.println("tearDownDatabase()\n");
    }
  
    @BeforeGroups(value="ui")
    public void setUpUI() {
        System.out.println("setUpUI()");
    }
  
    @AfterGroups(value="ui")
    public void tearDownUI() {
        System.out.println("tearDownUI()\n");
    }
 
    @Test(groups= "database")
    public void testadd(){
        System.out.println("testInsert()");
    }
     
    @Test(groups= "database")
    public void testUpdate(){
        System.out.println("testUpdate()");
    }
 
    @Test(groups= "database")
    public void testDelete(){
        System.out.println("testDelete()");
    }
 
    @Test(groups= "security")
    public void accessHomePage() {
        System.out.println("accessHomePage()");
    }
  
    @Test(groups= "security")
    public void accessAdminPage() {
        System.out.println("accessAdminPage()");
    }
 
    @Test(groups= "ui")
    public void openConfirmationDialog() {
        System.out.println("openConfirmationDialog()");
    }
 
    @Test(groups= "ui")
    public void openFileDialog() {
        System.out.println("openFileDialog()");
    }
}