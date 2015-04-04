package jcloner.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SourceObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<SourceListObject> list = new ArrayList<SourceListObject>();

	public void addObjectToList(SourceListObject object) {
		getList().add(object);
	}

	public List<SourceListObject> getList() {
		return list;
	}

}
