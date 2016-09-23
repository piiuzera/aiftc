package ftc.models;

public class Simbolo {
	private String value;
	
	public Simbolo() {
		setValue("");
	}
	public Simbolo(String value) {
		setValue(value);
	}
	
	@Override
	public String toString() {
		return this.value;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
