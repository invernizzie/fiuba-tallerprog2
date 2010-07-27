package main.model.filters;

public interface ParametricFilter extends Filter{
	public void setValue(double value);
	public double getValue();
	public String getFilterName();
	public double getDefaultValue();
	public double getTopLimit();
	public double getBottomLimit();
}
