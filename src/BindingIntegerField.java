import java.text.DecimalFormat;
import java.text.ParseException;

import com.jgoodies.binding.value.ValueModel;

@SuppressWarnings("serial")
public class BindingIntegerField extends BindingNumberField{
	
	public BindingIntegerField(ValueModel model) {
		super(model);
		setNumberFormat(new DecimalFormat("#,###,###,###"));
	}	

	@Override
	protected Number convertStringToNumber(String value) throws ParseException  {
		return getNumberFormat().parse(value).intValue();
	}

}
