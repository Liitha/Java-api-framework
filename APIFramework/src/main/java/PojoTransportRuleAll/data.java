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
public class data extends TransportRuleAllAction {
    private String type;
    private attributes attributes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PojoTransportRuleAll.attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(PojoTransportRuleAll.attributes attributes) {
        this.attributes = attributes;
    }




}
