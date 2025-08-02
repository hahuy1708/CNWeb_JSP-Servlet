package Model.bean;

public class Room {
    private int id;
    private String roomName;
    private int computerCount;
    private String description;
    private String status;
    public Room() {
    }

    public Room(int id, String roomName, int computerCount, String description, String status) {
		this.id = id;
		this.roomName = roomName;
		this.computerCount = computerCount;
		this.description = description;
		this.status = status;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getComputerCount() {
        return computerCount;
    }

    public void setComputerCount(int computerCount) {
        this.computerCount = computerCount;
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
}
