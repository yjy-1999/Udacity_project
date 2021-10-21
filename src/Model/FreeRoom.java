package Model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, Double price, RoomType enumeration) {
        super(roomNumber, (double) 0, enumeration);
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
