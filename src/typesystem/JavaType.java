package typesystem;

/**
 * the java type
 */
public class JavaType extends Type {

	private Class curClass;

	/**
	 * check if the current type can be assigned from another type
	 * 
	 * @param the type to be checked
	 * @return true if it can, otherwise false
	 */
	@Override
	public boolean canBeAssignedFrom(Type type) {
		// TODO
		// ASSUMING JAVA TYPE
		JavaType jtype = (JavaType) type;
		// TODO Auto-generated method stub
		boolean res = false;
		try {
			res = curClass.isAssignableFrom(((JavaType) type).getCurClass());
			
			// do the primitive & complex type check
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

	/**
	 * check if the primitive type can be assigned by the complex type
	 * 
	 * @param primitive the primitive type
	 * @param complex the complex type
	 * @return true if it can, otherwise false
	 */
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
