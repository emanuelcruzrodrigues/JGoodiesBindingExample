import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JTextField;

import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;

@SuppressWarnings("serial")
public abstract class BindingNumberField extends BindingTextField{

	protected ValueHolder valueHolder;
	protected ValueModel valueModel;
	
	protected boolean listening;
	
	protected NumberFormat numberFormat;
	
	public BindingNumberField(ValueModel model) {
		this(model, new ValueHolder());
	}
	
	private BindingNumberField(ValueModel model, ValueHolder valueHolder) {
		super(valueHolder, true);
		this.valueModel = model;
		this.valueHolder = valueHolder;
		
		setHorizontalAlignment(JTextField.RIGHT);
		
		initListeners();
		
		listening = true;
	}

	private void initListeners() {
		valueHolder.addValueChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (!listening) return;
				listening = false;
				String value = (String) valueHolder.getValue();
				
				try {
					
					Number number = (value == null || value.length() == 0) ? null : convertStringToNumber(value);
					valueModel.setValue(number);
					
					refreshValueHolderValue();
					
				} catch (ParseException e) {
					refreshValueHolderValue();
				}
				listening = true;
			}

		});
		
		valueModel.addValueChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (!listening) return;
				listening = false;
				
				refreshValueHolderValue();
				
				listening = true;
			}
		});
	}
	
	protected abstract Number convertStringToNumber(String value) throws ParseException;
	
	public void setNumberFormat(NumberFormat numberFormat) {
		this.numberFormat = numberFormat;
	}
	
	public NumberFormat getNumberFormat() {
		if (numberFormat == null){
			numberFormat = new DecimalFormat("#,##0");
		}
		return numberFormat;
	}

	private void refreshValueHolderValue() {
		String vhValue = valueModel.getValue() == null ? null : getNumberFormat().format(valueModel.getValue());
		valueHolder.setValue(vhValue);
	}
	

}
