package tubes1;

import java.util.*;

public class RoomManager {
	private static ArrayList listOfRoom;

	public RoomManager() {
		this.listOfRoom = new ArrayList<Ruangan>();
	}

	public static void addRoom(Ruangan room) {
		listOfRoom.add(room);
	}

	public static Ruangan getRoom(int idx) {
		return (Ruangan)listOfRoom.get(idx);
	}

	public static int numberOfRoom() {
		return listOfRoom.size();
	}

	public static Ruangan getRoomByRuang(String ruang) {
		for (int i = 0; i < numberOfRoom(); i++) {
			if (getRoom(i).getNama().equals(ruang)) {
				return getRoom(i);
			}
		}
		return null;
	}
}