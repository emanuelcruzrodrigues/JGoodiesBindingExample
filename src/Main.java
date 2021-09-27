import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	PresentationModel<Product> model;
	
	BindingIntegerField tfId;
	BindingTextField tfName;
	BindingDoubleField tfPrice;
	
	JButton btnSave;
	
	public Main() {
		initModels();
		initComponents();
		initLayout();
		
		Product product = new Product();
		product.setId(1);
		product.setName("Example");
		product.setPrice(10.05);
		
		model.setBean(product);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initModels() {
		model = new PresentationModel<Product>();
	}

	private void initComponents() {
		tfId = new BindingIntegerField(model.getModel("id"));
		
		tfName = new BindingTextField(model.getModel("name"), true);
		
		tfPrice = new BindingDoubleField(model.getModel("price"), new DecimalFormat("#,###.00"));
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(evt -> JOptionPane.showMessageDialog(this, String.format("<html>Saved object: %s</html>", model.getBean().toString())));
	}

	private void initLayout() {
		
		FormLayout layout = new FormLayout("pref, 5px, 60dlu");
		DefaultFormBuilder builder =new DefaultFormBuilder(layout);
		builder.border(new EmptyBorder(5, 5, 5, 5));
		
		builder.append("Id:", tfId);
		builder.nextLine();
		
		builder.append("Name:", tfName);
		builder.nextLine();
		
		builder.append("Price:", tfPrice);
		builder.nextLine();
		
		builder.append(btnSave, builder.getColumnCount());
		builder.nextLine();
		
		setLayout(new BorderLayout());
		add(builder.getPanel(), BorderLayout.CENTER);
	}
	
	public void showFrame() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main().showFrame();
	}

}
