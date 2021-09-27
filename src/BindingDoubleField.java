import java.text.NumberFormat;
import java.text.ParseException;

import com.jgoodies.binding.value.ValueModel;

@SuppressWarnings("serial")
public class BindingDoubleField extends BindingNumberField{
	
	public BindingDoubleField(ValueModel model, NumberFormat numberFormat) {
		super(model);
		setNumberFormat(numberFormat);
	}
	
	@Override
	protected Number convertStringToNumber(String value) throws ParseException {
		return getNumberFormat().parse(value).doubleValue();
	}

}
