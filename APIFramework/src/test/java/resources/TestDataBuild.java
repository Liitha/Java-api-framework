package resources;

import java.util.ArrayList;
import java.util.List;

import PojoDisableAllTransportRule.Attributes;
import PojoDisableAllTransportRule.Data;
import PojoDisableAllTransportRule.MainRequest;
import PojoTransportRuleAll.TransportRuleAllAction;
//import PojoTransportRuleAll.attributes;
import PojoTransportRuleAll.attributes;
import PojoTransportRuleAll.data;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

//	public TransportRuleAllAction TransportRuleAll(){
//		TransportRuleAllAction t= new TransportRuleAllAction();
//		t.
//	}


//	{
//        "data": {
//        "type": "disableRestoreAllTransportRules",
//        "attributes": {
//        "operation": "Disable",
//        "requestor": "abalila@appriver.com",
//        "requestId": "{{_requestid}}"
//        }
//        }
//        }
//	public attributes attributes(){
//		System.out.println("starting");
//		attributes a = new attributes();
//		a.setOperation("Disable");
//		a.setRequestor("abalila@appriver.com");
//		a.setRequestId("7f7cd6f0-92f8-4972-8e13-a6b86b08ca18");
//	}

		public TransportRuleAllAction TransportRuleAll(){

			attributes a= new attributes();
			a.setOperation("restore");
			a.setRequestor("abalila@appriver.com");
			a.setRequestId("a807b48c-ba55-4d0f-9d41-c9b113f34296");

			data d = new data();
			d.setType("disableRestoreAllTransportRules");
			d.setAttributes(a);

			return d;

		}


	public MainRequest DisableAllTransportRule(){
		Attributes attributes = new Attributes();
		attributes.setOperation("Disable");
		attributes.setRequestor("abalila@appriver.com");
		attributes.setRequestId("7c065850-1a1c-43de-8d9a-95bf85eb2e32");

		Data data = new Data();
		data.setType("disableRestoreAllTransportRules");
		data.setAttributes(attributes);

		MainRequest mainRequest = new MainRequest();
		mainRequest.setData(data);

	return mainRequest;

	}

	
	public AddPlace addPlacePayLoad(String name, String language, String address)
	{
		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}



}
