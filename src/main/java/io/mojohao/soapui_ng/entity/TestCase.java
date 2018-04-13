package io.mojohao.soapui_ng.entity;

public class TestCase {
    private int caseId;
    private int caseLibId;
    private String caseName;
    private String caseParaType;
    private String parameter;
    private String desiredResponse;
    private String caseInfo;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getCaseLibId() {
        return caseLibId;
    }

    public void setCaseLibId(int caseLibId) {
        this.caseLibId = caseLibId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseParaType() {
        return caseParaType;
    }

    public void setCaseParaType(String caseParaType) {
        this.caseParaType = caseParaType;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getDesiredResponse() {
        return desiredResponse;
    }

    public void setDesiredResponse(String desiredResponse) {
        this.desiredResponse = desiredResponse;
    }

    public String getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(String caseInfo) {
        this.caseInfo = caseInfo;
    }
}
