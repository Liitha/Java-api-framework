package PojoTransportRuleAll;

//{
//        "data": {
//        "type": "disableRestoreAllTransportRules",
//        "attributes": {
//        "operation": "Disable",
//        "requestor": "abalila@appriver.com",
//        "requestId": "{{_requestid}}"
//        }
//        }
//        }
public class attributes {
    private  String operation;
    private  String requestor;
    private  String requestId;
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }



}
