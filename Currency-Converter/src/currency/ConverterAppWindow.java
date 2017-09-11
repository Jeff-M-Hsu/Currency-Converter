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
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.awt.event.InputMethodEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class ConverterAppWindow {

	private JFrame frmCurrencyConverter;
	private JTextField txtEnterAmount;

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
		//icon credits to https://icons8.com/icon/36948/Initiate-Money-Transfer
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
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnNewMenu.add(mntmAbout);
		
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
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 268, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 1, 0, 0, 30, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmCurrencyConverter.getContentPane().setLayout(gridBagLayout);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 6;
		gbc_verticalStrut_1.gridy = 0;
		frmCurrencyConverter.getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 10;
		gbc_verticalStrut.gridy = 0;
		frmCurrencyConverter.getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 9;
		gbc_horizontalStrut_1.gridy = 2;
		frmCurrencyConverter.getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 4;
		gbc_horizontalStrut.gridy = 3;
		frmCurrencyConverter.getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		txtEnterAmount = new JTextField();
		txtEnterAmount.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterAmount.setSelectedTextColor(new Color(255, 255, 255));
		txtEnterAmount.setOpaque(true);
		txtEnterAmount.setToolTipText("Enter amount");
		txtEnterAmount.setFont(new Font("SansSerif", Font.PLAIN, 20));
		txtEnterAmount.setMargin(new Insets(10, -7, 10, -7));
		GridBagConstraints gbc_txtEnterAmount = new GridBagConstraints();
		gbc_txtEnterAmount.fill = GridBagConstraints.BOTH;
		gbc_txtEnterAmount.insets = new Insets(0, 0, 5, 5);
		gbc_txtEnterAmount.gridx = 7;
		gbc_txtEnterAmount.gridy = 4;
		frmCurrencyConverter.getContentPane().add(txtEnterAmount, gbc_txtEnterAmount);
		frmCurrencyConverter.pack();
		txtEnterAmount.setColumns(10);
		txtEnterAmount.setText("Enter amount");
		txtEnterAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(!txtEnterAmount.getText().matches("[0-9]+")) {
					txtEnterAmount.setText("");
				}
			}
		});
		txtEnterAmount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if(!txtEnterAmount.getText().matches("[0-9]+")) {
					txtEnterAmount.setText("");
				}
			}
		});
		
		JButton btnNewButton = new JButton("Convert!");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					TargetCurrencyUI dialog = new TargetCurrencyUI();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					System.out.println("Catastrophic application failure");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 5;
		frmCurrencyConverter.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_3.gridx = 8;
		gbc_horizontalStrut_3.gridy = 6;
		frmCurrencyConverter.getContentPane().add(horizontalStrut_3, gbc_horizontalStrut_3);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_2.gridx = 5;
		gbc_horizontalStrut_2.gridy = 7;
		frmCurrencyConverter.getContentPane().add(horizontalStrut_2, gbc_horizontalStrut_2);
	}
}
