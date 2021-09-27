import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Product {
	
	private Integer id;
	private String name;
	private Double price;
	
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	public void addPropertyChangeListener(PropertyChangeListener l) {
		changeSupport.addPropertyChangeListener(l);
	}
	public void removePropertyChangeListener(PropertyChangeListener l) {
		changeSupport.removePropertyChangeListener(l);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		Object oldValue = this.id;
		this.id = id;
		changeSupport.firePropertyChange("id", oldValue, id);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		Object oldValue = this.name;
		this.name = name;
		changeSupport.firePropertyChange("name", oldValue, name);
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		Object oldValue = this.price;
		this.price = price;
		changeSupport.firePropertyChange("price", oldValue, price);
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("<ul>");
		if (getId() != null) {
			result.append(String.format("<li>Id: %d</li>", getId().intValue()));
		}
		if (getName() != null) {
			result.append(String.format("<li>Name: %s</li>", getName()));
		}
		if (getPrice() != null) {
			result.append(String.format("<li>Price: %f</li>", getPrice().doubleValue()));
		}
		result.append("</ul>");
		
		return result.toString();
	}

}
