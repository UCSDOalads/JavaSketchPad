package paintcomponents.data;

import paintcomponents.data.DataFromPoint;
import paintcomponents.data.DataLineSegment;
import paintcomponents.data.DataToPoint;
	

public final class TypeCaster<E> {
	private DataFromPoint fromPoint;
	private DataToPoint toPoint;
	private Class exptectedClass;
	private String expectedType;
	public TypeCaster(DataLineSegment line){
		this.fromPoint=line.getFromPoint();
		this.toPoint=line.getToPoint();
	}
	protected void convertAction() throws DataFromPointNoDataProviderException, DataFromPointProviderCannotProvideDataException{
		this.exptectedClass = this.toPoint.getExpectedType().getCurClass();
		this.expectedType=this.toPoint.getExpectedType().getCurClass().getName();
	    switch (expectedType){
	    case "int":
	    	 Integer.valueOf((String) fromPoint.getData());
	    }
		//Not finished
	}
}
