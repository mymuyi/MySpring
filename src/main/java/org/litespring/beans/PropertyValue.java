package org.litespring.beans;

/**
 * 保存 bean 的 property
 * @author 木易
 *
 */
public class PropertyValue {

	private String name;

	private Object value;

	private boolean isConverted = false;

	private Object convertedObject;

	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Object getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}

	public synchronized boolean isConverted() {
		return this.isConverted;
	}

	public synchronized Object getConvertedObject() {
		return convertedObject;
	}

	public synchronized void setConvertedObject(Object value) {
		this.isConverted = true;
		this.convertedObject = value;
	}

}
