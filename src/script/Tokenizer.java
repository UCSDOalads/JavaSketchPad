package script;

import java.util.ArrayList;


public class Tokenizer {
	private enum State { INIT, IN_DOUBLE_QUOTE, IN_SINGLE_QUOTE, IN_DOUBLE_QUOTE_ESCAPE, IN_SINGLE_QUOTE_ESCAPE, INIT_ESCAPE}
  private State state;
	private char[] array;
	private ArrayList<String> output;
	private String pending;

	// ctor
	public Tokenizer(String input) {
		state = State.INIT;
		array = input.toCharArray();
		output = new ArrayList<String>();
		pending = "";
		
		for (int i = 0; i < array.length; i++) {
      interpretChar(array[i]);
		}
		
		if (!pending.equals("")) {
			output.add(pending);
			pending = "";
		}
	}

	/**
	 * @return an array list of tokens
	 */
	public ArrayList<String> getTokens() {
		
		return output;

	}

	/**
	 * interpret characters according to a finite state machine
	 * @param a character
	 */
	private void interpretChar(char c) {

		switch(state) {
		case INIT:
			switch(c) {
			case '\\':
				state = State.INIT_ESCAPE;
				break;
				
			case '"':
				state = State.IN_DOUBLE_QUOTE;
				break;
				
			case '\'':
				state = State.IN_SINGLE_QUOTE;
				break;
				
			case ' ':
				output.add(pending);
				pending = "";
				break;
				
			default:
				pending += c;
				break;
			}
			break;
			
		case IN_DOUBLE_QUOTE:
			switch(c) {
			case '\\':
				state = State.IN_DOUBLE_QUOTE_ESCAPE;
				break;
				
			case '"':
				state = State.INIT;
				break;
				
			default:
				pending += c;
				break;
			}
			break;
			
		case IN_SINGLE_QUOTE:
			switch(c) {
			case '\\':
				state = State.IN_SINGLE_QUOTE_ESCAPE;
				break;
				
			case '\'':
				state = State.INIT;
				break;
				
			default:
				pending += c;
				break;
			}
			
		case INIT_ESCAPE:
			pending += c;
			state = State.INIT;
			break;
			
		case IN_DOUBLE_QUOTE_ESCAPE:
			pending += c;
			state = State.IN_DOUBLE_QUOTE;
			break;
			
		case IN_SINGLE_QUOTE_ESCAPE:
			pending += c;
			state = State.IN_SINGLE_QUOTE;
			break;
		}
	}
}
