package pl.coderslab.entity;

public class Reservation {
	private int id;
	private int userId;
	private int roomId;
	private String created;
	private String dateFrom;
	private String dateTo;
	private String description;
	private String status;
	
	
	public Reservation () {
		
	}
	
	
	public Reservation(int userId, int roomId, String created, String dateFrom, String dateTo, String description,
			String status) {
		this.userId = userId;
		this.roomId = roomId;
		this.created = created;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.description = description;
		this.status = status;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
