/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Appointment;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nipun
 */
@XmlRootElement(name = "appointment")
public class AppointmentModel {

    private String appointmentId;
    private String nic;
    private String testId;
    private String testName;
    private byte[] testReport;
    private String technician;
    private String testCategory;
    private String appointedDate;
    private String appointmentDate;
    private String appointmentTime;
    private String doctor;
    private String status;
    private String userId;
    private String appointedBy;
    private String nettotal;
    private String payType;
    private String patientId;
    private int currentCount;

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the testId
     */
    public String getTestId() {
        return testId;
    }

    /**
     * @param testId the testId to set
     */
    public void setTestId(String testId) {
        this.testId = testId;
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
     * @return the appointedDate
     */
    public String getAppointedDate() {
        return appointedDate;
    }

    /**
     * @param appointedDate the appointedDate to set
     */
    public void setAppointedDate(String appointedDate) {
        this.appointedDate = appointedDate;
    }

    /**
     * @return the appointmentDate
     */
    public String getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * @param appointmentDate the appointmentDate to set
     */
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * @return the doctor
     */
    public String getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the nettotal
     */
    public String getNettotal() {
        return nettotal;
    }

    /**
     * @param nettotal the nettotal to set
     */
    public void setNettotal(String nettotal) {
        this.nettotal = nettotal;
    }

    /**
     * @return the payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType the payType to set
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * @return the appointmentId
     */
    public String getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId the appointmentId to set
     */
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return the appointedBy
     */
    public String getAppointedBy() {
        return appointedBy;
    }

    /**
     * @param appointedBy the appointedBy to set
     */
    public void setAppointedBy(String appointedBy) {
        this.appointedBy = appointedBy;
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
     * @return the appointmentTime
     */
    public String getAppointmentTime() {
        return appointmentTime;
    }

    /**
     * @param appointmentTime the appointmentTime to set
     */
    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    /**
     * @return the currentCount
     */
    public int getCurrentCount() {
        return currentCount;
    }

    /**
     * @param currentCount the currentCount to set
     */
    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    /**
     * @return the technician
     */
    public String getTechnician() {
        return technician;
    }

    /**
     * @param technician the technician to set
     */
    public void setTechnician(String technician) {
        this.technician = technician;
    }

    /**
     * @return the testReport
     */
    public byte[] getTestReport() {
        return testReport;
    }

    /**
     * @param testReport the testReport to set
     */
    public void setTestReport(byte[] testReport) {
        this.testReport = testReport;
    }

    /**
     * @return the patientId
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
