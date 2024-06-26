package busreservation;

public class BusModal {

	private int bus_no;
	private String ac;
	private int capacity;
	
	
	public BusModal() {
		super();
	}


	public BusModal(int bus_no, String ac, int capacity) {
		super();
		this.bus_no = bus_no;
		this.ac = ac;
		this.capacity = capacity;
	}


	public int getBus_no() {
		return bus_no;
	}


	public void setBus_no(int bus_no) {
		this.bus_no = bus_no;
	}


	public String getAc() {
		return ac;
	}


	public void setAc(String ac) {
		this.ac = ac;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
