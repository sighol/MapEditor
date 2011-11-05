package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author sigurd
 */
public class OptionsHolder implements Iterable<Option> {

	private List<Option> list;
	private String name = "Name is not yet set";
	
	public OptionsHolder() {
		list = new ArrayList<Option>();
	}

	public OptionsHolder(List<Option> list, String name) {
		this.list = list;
		this.name = name;
	}

	@Override
	public Iterator<Option> iterator() {
		return list.iterator();
	}

	public Option get(int index) {
		return list.get(index);
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public String toString() {
		return name;
	}

	public boolean add(Option e) {
		return list.add(e);
	}
}
