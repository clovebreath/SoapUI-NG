package io.mojohao.soapui_ng.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class TestResult {

    private int resultId;
    private String testId;
    private int caseId;
    private int testPlanId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Timestamp testDate;

    private String desiredResponse;
    private String actualResponse;
    private String assertion;

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }
    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public Timestamp getTestDate() {
        return testDate;
    }

    public void setTestDate(Timestamp testDate) {
        this.testDate = testDate;
    }

    public String getDesiredResponse() {
        return desiredResponse;
    }

    public void setDesiredResponse(String desiredResponse) {
        this.desiredResponse = desiredResponse;
    }

    public String getActualResponse() {
        return actualResponse;
    }

    public void setActualResponse(String actualResponse) {
        this.actualResponse = actualResponse;
    }

    public String getAssertion() {
        return assertion;
    }

    public void setAssertion(String assertion) {
        this.assertion = assertion;
    }

    public int getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(int testPlanId) {
        this.testPlanId = testPlanId;
    }
}
