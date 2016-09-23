import java.util.*;

public class RoomManager {
	private static ArrayList listOfRoom = new ArrayList<Room>();

	public static void addRoom(Room room) {
		listOfRoom.add(room);
	}

	public static Room getRoom(int idx) {
		return (Room)listOfRoom.get(idx);
	}

	public static int numberOfRoom() {
		return listOfRoom.size();
	}
}