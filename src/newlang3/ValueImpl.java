package newlang3;

public class ValueImpl implements Value {
	private String value;
	private ValueType type;

	public ValueImpl(String s) {
		value = s;
		type = ValueType.STRING;
	}

	public ValueImpl(int i) {
		value = Integer.toString(i);
		type = ValueType.INTEGER;
	}

	public ValueImpl(double d) {
		value = Double.toString(d);
		type = ValueType.DOUBLE;
	}

	public ValueImpl(boolean b) {
		value = Boolean.toString(b);
		type = ValueType.BOOL;
	}

	@Override
	public String getSValue() {
		return value;
	}

	@Override
	public int getIValue() {
		return Integer.parseInt(value);
	}

	@Override
	public double getDValue() {
		return Double.parseDouble(value);
	}

	@Override
	public boolean getBValue() {
		return Boolean.parseBoolean(value);
	}

	@Override
	public ValueType getType() {
		return this.type;
	}
}
