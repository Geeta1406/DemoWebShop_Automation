package basepage;

public class ProductDetails {


	private String name;
	private String price;
	private String url;

	public ProductDetails(String name, String price, String url) {
		
		this.name = name;
		this.price = price;
		this.url = url;
	}

	public String getName(){

		return name;
	}

	public String getPrice() { 
		return price;
	}

	public String getUrl() { 

		return url; 
	}

}
