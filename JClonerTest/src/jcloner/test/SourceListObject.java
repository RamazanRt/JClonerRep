package jcloner.test;

import java.io.Serializable;

public class SourceListObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String objectName = "";

	public SourceListObject(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectName() {
		return objectName;
	}

}
