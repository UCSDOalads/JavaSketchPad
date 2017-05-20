package typesystem;

public abstract class Type {
	
	public abstract boolean canBeAssignedFrom(Type type);
	public abstract Class getCurClass();
}
