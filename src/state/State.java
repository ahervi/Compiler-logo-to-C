package state;

import java.util.HashMap;

@SuppressWarnings("serial")
public class State<E> extends HashMap<String, E> {
	public State() {
		super();
	}
	// returns the value of a variable in the state
	public E lookup(String var) {
		return get(var);
	}
	// binds a variable in the state
	public void bind(String var, E value) {
		put(var, value);
	}
}
