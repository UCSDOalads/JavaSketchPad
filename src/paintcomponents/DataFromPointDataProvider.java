package paintcomponents;


public interface DataFromPointDataProvider<T> {

	public T provideInformationToDataFromPoint(DataFromPoint<T> dataFromPoint);
	public boolean canProvideInformationToDataFromPoint(DataFromPoint<T> dataFromPoint);
		
	
	
}
