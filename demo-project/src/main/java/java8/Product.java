package java8;
import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
	
	private static final long serialVersionUID = -1338754691448458831L;
	
	private String code;
    private String name;
    private double price;
    private byte[] image;
 
    // For sort.
    private Date createDate;
 
    public Product() {
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public Date getCreateDate() {
        return createDate;
    }
 
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
 
    public byte[] getImage() {
        return image;
    }
 
    public void setImage(byte[] image) {
        this.image = image;
    }

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", price=" + price + ", createDate=" + createDate + "]";
	}
 
}
