package currency;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.awt.List;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class TargetCurrencyUI extends JDialog {

	/**
	 * Create the dialog.
	 */
	public TargetCurrencyUI() {
		setResizable(false);
		setTitle("Enter currencies");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TargetCurrencyUI.class.getResource("/resources/icon.png")));
		setBounds(100, 100, 248, 276);
		getContentPane().setLayout(null);
		try {
			List selectedList = new List();
			selectedList.setBounds(0, 26, 242, 193);
			getContentPane().add(selectedList);


			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.setBounds(0, 0, 242, 27);
			getContentPane().add(comboBox);
			File input = new File("abbreviations.txt");
			BufferedReader list = new BufferedReader(new FileReader(input));
			String addItem = "";
			while((addItem = list.readLine()) != null){
				comboBox.addItem(addItem);
			}
			list.close();

			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBounds(0, 215, 242, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);

			JButton addButton= new JButton("Add");
			addButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					selectedList.add(comboBox.getSelectedItem().toString());
				}
			});
			buttonPane.add(addButton);


			JButton okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);


			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);


		} catch (FileNotFoundException e) {
			System.out.println("can't find file");
		} catch (IOException e) {
			System.out.println("can't read file");
		}
	}
}

