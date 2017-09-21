package currency;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Insets;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ButtonGroup;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ConverterAppWindow {

	private JFrame frmCurrencyConverter;
	private JTextField txtEnterAmount;
	private double amount;
	private String[] target = new String[1];
	private String mode;
	private JTextField txtYyyymmdd;
	private String date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConverterAppWindow window = new ConverterAppWindow();
					window.frmCurrencyConverter.setVisible(true);
				} catch (Exception e) {
					System.out.println("Catastrophic application failure");
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConverterAppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmCurrencyConverter = new JFrame();
		frmCurrencyConverter.getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 20));
		frmCurrencyConverter.setResizable(false);
		frmCurrencyConverter.setPreferredSize(new Dimension(480, 320));
		frmCurrencyConverter.setForeground(new Color(102, 102, 102));
		frmCurrencyConverter.setBackground(new Color(102, 102, 102));
		frmCurrencyConverter.setIconImage(Toolkit.getDefaultToolkit().getImage(ConverterAppWindow.class.getResource("/resources/icon.png")));
		frmCurrencyConverter.setTitle("Currency Converter");
		frmCurrencyConverter.setBounds(100, 100, 317, 256);
		frmCurrencyConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmCurrencyConverter.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNewMenu.add(mntmExit);
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				WindowEvent close = new WindowEvent(frmCurrencyConverter, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(close);
			}
		});
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{268, 0};
		gridBagLayout.rowHeights = new int[]{0, 1, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmCurrencyConverter.getContentPane().setLayout(gridBagLayout);

		txtEnterAmount = new JTextField();
		txtEnterAmount.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterAmount.setSelectedTextColor(new Color(255, 255, 255));
		txtEnterAmount.setOpaque(true);
		txtEnterAmount.setToolTipText("Enter amount");
		txtEnterAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtEnterAmount.setMargin(new Insets(10, -7, 10, -7));
		GridBagConstraints gbc_txtEnterAmount = new GridBagConstraints();
		gbc_txtEnterAmount.fill = GridBagConstraints.BOTH;
		gbc_txtEnterAmount.insets = new Insets(0, 0, 5, 0);
		gbc_txtEnterAmount.gridx = 0;
		gbc_txtEnterAmount.gridy = 4;
		frmCurrencyConverter.getContentPane().add(txtEnterAmount, gbc_txtEnterAmount);
		frmCurrencyConverter.pack();
		txtEnterAmount.setColumns(10);
		txtEnterAmount.setText("Enter amount");
		txtEnterAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(!txtEnterAmount.getText().matches("([1-9]+\\.?\\d*)")) {
					txtEnterAmount.setText("");
				}
			}
		});
		txtEnterAmount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if(!txtEnterAmount.getText().matches("([1-9]+\\.?\\d*)")) {
					txtEnterAmount.setText("");
				}
			}
		});

		JButton btnNewButton = new JButton("Convert!");
		btnNewButton.setEnabled(true);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					try {
						amount = Double.parseDouble(txtEnterAmount.getText());
						OutputWindow dialog = new OutputWindow();
						txtYyyymmdd.getText();
						dialog.setFields(amount, target, mode, date);
						dialog.request();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception f) {
						f.printStackTrace();
					}
				} catch (Exception e1) {
					System.out.println("Catastrophic application failure");
				}
			}
		});

		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Live");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton.gridx = 0;
		gbc_rdbtnNewRadioButton.gridy = 5;
		frmCurrencyConverter.getContentPane().add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);

		final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Historical");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_1.gridx = 0;
		gbc_rdbtnNewRadioButton_1.gridy = 6;
		frmCurrencyConverter.getContentPane().add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);

		ButtonGroup rdbt = new ButtonGroup();
		rdbt.add(rdbtnNewRadioButton);
		rdbt.add(rdbtnNewRadioButton_1);

		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnNewRadioButton.isSelected()) {
					mode = rdbtnNewRadioButton.getText();
					txtYyyymmdd.setEnabled(false);
					txtYyyymmdd.setText("yyyy-mm-dd");
				}
			}
		});
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(rdbtnNewRadioButton_1.isSelected()) {
					mode = rdbtnNewRadioButton_1.getText();
					txtYyyymmdd.setEnabled(true);
					txtYyyymmdd.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent arg0) {
							if("yyyy-mm-dd".equalsIgnoreCase(txtYyyymmdd.getText())) {
								txtYyyymmdd.setText("");
							}
						}
					});
				}
			}
		});

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				target[0] = comboBox.getSelectedItem().toString();
			}
		});

		txtYyyymmdd = new JTextField();
		txtYyyymmdd.setHorizontalAlignment(SwingConstants.CENTER);
		txtYyyymmdd.setEnabled(false);
		txtYyyymmdd.setText("yyyy-mm-dd");
		txtYyyymmdd.setToolTipText("yyyy-mm-dd");
		GridBagConstraints gbc_txtYyyymmdd = new GridBagConstraints();
		gbc_txtYyyymmdd.insets = new Insets(0, 0, 5, 0);
		gbc_txtYyyymmdd.gridx = 0;
		gbc_txtYyyymmdd.gridy = 7;
		frmCurrencyConverter.getContentPane().add(txtYyyymmdd, gbc_txtYyyymmdd);
		txtYyyymmdd.setColumns(10);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		comboBox.setEditable(false);
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 8;
		frmCurrencyConverter.getContentPane().add(comboBox, gbc_comboBox);
		File input = new File("abbreviations.txt");
		BufferedReader list;
		try {
			list = new BufferedReader(new FileReader(input));
			String addItem = "";
			while((addItem = list.readLine()) != null){
				comboBox.addItem(addItem);
			}
			list.close();

		} catch (FileNotFoundException e) {
			System.out.println("can't find file");
		} catch (IOException e) {
			System.out.println("can't read file");
		}

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 9;
		frmCurrencyConverter.getContentPane().add(btnNewButton, gbc_btnNewButton);
	}
}
