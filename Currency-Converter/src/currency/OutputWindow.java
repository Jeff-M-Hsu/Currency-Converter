package currency;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class OutputWindow extends JDialog {

	private double amount;
	private String[] target;
	private String mode;
	private String date;
	public String output;

	public void setFields(double amount, String[] target, String mode, String date) {
		this.amount = amount;
		this.target = target;
		this.mode = mode;
		this.date = date;
	}

	public void request() {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream sysout = new PrintStream(out);
			PrintStream old = new PrintStream(System.out);
			System.setOut(sysout);
			if("live".equalsIgnoreCase(mode)) {
				RequestLive.requestLive(target,amount);
				output = out.toString();
				System.out.flush();
				System.setOut(System.out);
				System.out.println(old);
			}
			if("historical".equalsIgnoreCase(mode)) {
				RequestHistory.requestHistory(target,amount,date);
				output = out.toString();
				System.out.flush();
				System.setOut(System.out);
				System.out.println(old);
			}

			String[] formatOut = output.split("\\n");
			JLabel lblNewLabel = new JLabel(formatOut[2]);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 3;
			getContentPane().add(lblNewLabel, gbc_lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel(formatOut[3]);
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 4;
			getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel(formatOut[4]);
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 5;
			getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		} catch(Exception e) {
			JLabel errorLabel = new JLabel("Please enter valid information in the specified format");
			GridBagConstraints gbc_errorLabel = new GridBagConstraints();
			gbc_errorLabel.insets = new Insets(0, 0, 5, 0);
			gbc_errorLabel.gridx = 0;
			gbc_errorLabel.gridy = 4;
			getContentPane().add(errorLabel, gbc_errorLabel);
		}
	}

	/**
	 * Create the dialog.
	 */
	public OutputWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OutputWindow.class.getResource("/resources/icon.png")));
		setTitle("Result");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 227);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.SOUTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 8;
			getContentPane().add(buttonPane, gbc_buttonPane);
			JButton okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);

		}
	}
}
