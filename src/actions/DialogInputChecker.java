package actions;

/**
 * DialogInput Class check if a dialog input is valid
 * @author kaijiecai
 *
 */
public class DialogInputChecker {

	final int DECIMAL_BASE=10;
	public DialogInputChecker(){
		
	}
	
	/**
	 * check if input is valid
	 * @param str String to be checked
	 * @return true if it is not empty, false otherwise
	 */
	public boolean isEmpty(String str){
		if(str == null || str.isEmpty()){
			return true;
		}
		return false;
	}
	
	
	/**
	 * check if str is a valid number input
	 * @param str String to be checked
	 * @return true if input is valid number, false otherwise
	 */
	public boolean isValidNumber(String str){
		if(!isEmpty(str)){
			try{
				Integer.parseInt(str,DECIMAL_BASE);
				return true;
			}
			catch(Exception e){
			}
		}
		return false;
	}
	
	/**
	 * Check if str is a valid number input within min and max inclusive
	 * @param str string to be checked
	 * @param min 
	 * @param max
	 * @return true if input is valid, false otherwise
	 */
	public boolean isValidNumber(String str, int min, int max){
		if(isValidNumber(str)){
			if(max >= min){
				int num = Integer.parseInt(str,DECIMAL_BASE);
				if(num >= min && num <= max)
					return true;
			}
		}
		return false;
	}
}
