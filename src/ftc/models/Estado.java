package ftc.models;

public class Estado {
	private String value;
	
	public Estado() {
		setValue("");
	}
	public Estado(String value) {
		setValue(value);
	}
	
	@Override
	public String toString() {
		return value;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
