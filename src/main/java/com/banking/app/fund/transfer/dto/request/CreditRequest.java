package com.banking.app.fund.transfer.dto.request;

public class CreditRequest extends TransactionRequest{
    private String creditSource;            // Type of credit (SALARY, REFUND, UPI, NEFT, CASH, etc.)
    private String approvedBy;              // Optional: For branch approval flow
    private String mode;                    // CASH, CHEQUE, ONLINE, etc.
    private String purposeCode;             // For compliance/reporting (e.g., INCOME, LOAN_REPAYMENT)
    private String sourceInstitutionCode;   // For interbank transfers or APIs like NEFT/IMPS

    public String getCreditSource() {
        return creditSource;
    }

    public void setCreditSource(String creditSource) {
        this.creditSource = creditSource;
    }
    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    public String getSourceInstitutionCode() {
        return sourceInstitutionCode;
    }

    public void setSourceInstitutionCode(String sourceInstitutionCode) {
        this.sourceInstitutionCode = sourceInstitutionCode;
    }



    @Override
    public String toString() {
        return "CreditRequest{" +
                "creditSource='" + creditSource + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                ", mode='" + mode + '\'' +
                ", purposeCode='" + purposeCode + '\'' +
                ", sourceInstitutionCode='" + sourceInstitutionCode + '\'' +
                '}';
    }
}
