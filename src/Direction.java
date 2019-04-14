public enum Direction {
	
	FORWARD("forward"),
	BACKWARD("backward"),
	UP("up"),
	DOWN("down");
	
	private String name = null;
	
	private Direction(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}
}
