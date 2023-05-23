/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.MedicalTest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nipun
 */
@XmlRootElement(name = "medicaltest")
public class MedicalTestModel {
    
    
    private String testCategory;
    private int testCategoryId;
    private String pastTestCategory;
    private String labNo;
    private String maxCount;
    private String empName;
    private int empId;
    
    private String testName;
    private String pastTestName;
    private String price;
    
    
    public MedicalTestModel(){
        
    }

    /**
     * @return the testCategory
     */
    public String getTestCategory() {
        return testCategory;
    }

    /**
     * @param testCategory the testCategory to set
     */
    public void setTestCategory(String testCategory) {
        this.testCategory = testCategory;
    }

    /**
     * @return the labNo
     */
    public String getLabNo() {
        return labNo;
    }

    /**
     * @param labNo the labNo to set
     */
    public void setLabNo(String labNo) {
        this.labNo = labNo;
    }

    /**
     * @return the maxCount
     */
    public String getMaxCount() {
        return maxCount;
    }

    /**
     * @param maxCount the maxCount to set
     */
    public void setMaxCount(String maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * @return the empName
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return the testName
     */
    public String getTestName() {
        return testName;
    }

    /**
     * @param testName the testName to set
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the empId
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * @return the pastTestCategory
     */
    public String getPastTestCategory() {
        return pastTestCategory;
    }

    /**
     * @param pastTestCategory the pastTestCategory to set
     */
    public void setPastTestCategory(String pastTestCategory) {
        this.pastTestCategory = pastTestCategory;
    }

    /**
     * @return the testCategoryId
     */
    public int getTestCategoryId() {
        return testCategoryId;
    }

    /**
     * @param testCategoryId the testCategoryId to set
     */
    public void setTestCategoryId(int testCategoryId) {
        this.testCategoryId = testCategoryId;
    }

    /**
     * @return the pastTestName
     */
    public String getPastTestName() {
        return pastTestName;
    }

    /**
     * @param pastTestName the pastTestName to set
     */
    public void setPastTestName(String pastTestName) {
        this.pastTestName = pastTestName;
    }
    
}
