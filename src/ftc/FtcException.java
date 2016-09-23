package ftc;

public class FtcException extends Exception {
	
	private static final long serialVersionUID = 4235335868012553770L;
	
	private String code;
	private String details;
	private String fullMessage;
	
	public FtcException(String message) {
		super(message);
		this.code = "";
		this.details = "";
		this.fullMessage = message;
	}
	public FtcException(String message, String code) {
		super(message);
		this.code = code;
		this.details = "";
		this.fullMessage = code + " - " + message;
	}
	public FtcException(String message, String code, String details) {
		super(message);
		this.code = code;
		this.details = details;
		this.fullMessage = code + " - " + message;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDetails() {
		return details;
	}
	
	public String getFullMessage() {
		return fullMessage;
	}
	
}
