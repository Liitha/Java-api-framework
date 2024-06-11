package resources;
//enum is special class in java which has collection of constants or  methods
public enum APIResources {

	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json"),
	disableAllTransportRule("/disableRestoreAllTransportRules"),
	disableAllGetTransportRule("/disableRestoreAllTransportRules/{id}"),
	transportRuleAllAPI("/disableRestoreAllTransportRules");


	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}

	public String getResourceWithId(String id) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		return resource.replace("{id}", id);
	}
}
