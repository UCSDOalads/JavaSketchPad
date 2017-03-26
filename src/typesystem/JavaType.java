package typesystem;

public class JavaType extends Type {

	private Class curClass;

	@Override
	public boolean canBeAssignedFrom(Type type) {
		// TODO
		// ASSUMING JAVA TYPE
		JavaType jtype = (JavaType) type;
		// TODO Auto-generated method stub
		boolean res = false;
		try {
			res = curClass.isAssignableFrom(((JavaType) type).getCurClass());
			if (res == false) {
				if (this.getCurClass().isPrimitive()) {
					res = isPrimitiveAssignableFromClass(curClass, jtype.curClass);
				} else if (jtype.curClass.isPrimitive()) {
					res = isPrimitiveAssignableFromClass(jtype.curClass, curClass);
				}
			}
		} catch (ClassCastException e) {
			res = false;
		}
		return res;
	}

	public JavaType(Class cls) {
		curClass = cls;

	}

	public Class getCurClass() {
		return curClass;
	}

	private boolean isPrimitiveAssignableFromClass(Class primitive,
			Class complex) {
		if (primitive == int.class) {
			return complex == Integer.class;
		}		
		if (primitive == double.class) {
			return complex == Double.class;
		}		
		if (primitive == float.class) {
			return complex == Float.class;
		}		
		if (primitive == char.class) {
			return complex == Character.class;
		}		
		if (primitive == boolean.class) {
			return complex == Boolean.class;
		}		
		if (primitive == byte.class) {
			return complex == Byte.class;
		}		
		if (primitive == short.class) {
			return complex == Short.class;
		}		
		if (primitive == long.class) {
			return complex == Long.class;
		}
		return false;
	}
}
