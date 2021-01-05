package Windows;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import JDBCCode.MainJDBC;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

public class MainWindow extends MainJDBC {

	private static JFrame frame;
	ImageIcon imgicon = new ImageIcon("icon.png");
	ImageIcon imgdelete = new ImageIcon("delete.png");
	ImageIcon imgedit = new ImageIcon("edit.png");
	ImageIcon imgcalcu = new ImageIcon("calcu.png");
	ImageIcon imgadd = new ImageIcon("add.png");
	ImageIcon imgprint = new ImageIcon("print.png");
	ImageIcon imgcheck = new ImageIcon("check.png");
	ImageIcon imgok = new ImageIcon("ok.png");
	ImageIcon imgnotok = new ImageIcon("notok.png");
	ImageIcon imgdeletetable = new ImageIcon("deletetable.png");


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					frame.setTitle("Stock Manager");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initialize();
	}

	JLayeredPane layeredPane1 = new JLayeredPane();
	JLayeredPane layeredPane2 = new JLayeredPane();
	private JTable table;
	private JTextField textFieldStockName;
	private JTextField textFieldBuyingprice;
	private JTextField textFieldSellingprice;
	JLabel investmoney = new JLabel("Invest Money:");
	JLabel profitlossmoney = new JLabel("Profit :");
	private JTextField textFieldRemoveStock;
	private JTextField textFieldCSVfilename;
	private JTextField textFieldnum1;
	private JTextField textFieldnum2;
	JLabel lblOperation = new JLabel("?");
	private JTextField textFieldSTockQuantity;
	private JTextField textFieldeditstockid;
	private JTextField textFieldNewvalue;

	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(232, 211, 255));
		frame.getContentPane().setLayout(null);

		layeredPane1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		layeredPane1.setBounds(230, 21, 1072, 147);
		frame.getContentPane().add(layeredPane1);
		layeredPane1.setLayout(new CardLayout(0, 0));

		JPanel PanelWelcome = new JPanel();
		PanelWelcome.setBackground(new Color(222, 236, 255));
		layeredPane1.add(PanelWelcome, "name_66956127543400");
		PanelWelcome.setLayout(null);

		JLabel lblWelcomeToStock = new JLabel("Wel-Come to Stock Manager");
		lblWelcomeToStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToStock.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblWelcomeToStock.setBounds(226, 10, 597, 106);
		PanelWelcome.add(lblWelcomeToStock);

		JPanel PanelAddstock = new JPanel();
		PanelAddstock.setBackground(new Color(222, 236, 255));
		layeredPane1.add(PanelAddstock, "name_173078897202800");
		PanelAddstock.setLayout(null);

		JLabel lblStockName = new JLabel("Stock Name:");
		lblStockName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStockName.setBounds(55, 37, 85, 19);
		PanelAddstock.add(lblStockName);

		textFieldStockName = new JTextField();
		textFieldStockName.setBounds(165, 38, 96, 19);
		PanelAddstock.add(textFieldStockName);
		textFieldStockName.setColumns(10);

		JLabel lblBuyingDate = new JLabel("Buying Date:");
		lblBuyingDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBuyingDate.setBounds(313, 37, 96, 19);
		PanelAddstock.add(lblBuyingDate);

		JDateChooser dateChooserBuyingdate = new JDateChooser();
		dateChooserBuyingdate.setBounds(419, 37, 96, 19);
		PanelAddstock.add(dateChooserBuyingdate);

		JLabel lblBuyingPrice = new JLabel("Buying Price:");
		lblBuyingPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBuyingPrice.setBounds(313, 80, 96, 16);
		PanelAddstock.add(lblBuyingPrice);

		textFieldBuyingprice = new JTextField();
		textFieldBuyingprice.setBounds(419, 80, 96, 19);
		PanelAddstock.add(textFieldBuyingprice);
		textFieldBuyingprice.setColumns(10);

		JLabel lblSellingDate = new JLabel("Selling Date:");
		lblSellingDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSellingDate.setBounds(564, 41, 96, 19);
		PanelAddstock.add(lblSellingDate);

		JDateChooser dateChooserSellingday = new JDateChooser();
		dateChooserSellingday.setBounds(659, 37, 96, 19);
		PanelAddstock.add(dateChooserSellingday);

		JLabel lblSellingPrice = new JLabel("Selling Price:");
		lblSellingPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSellingPrice.setBounds(564, 83, 96, 19);
		PanelAddstock.add(lblSellingPrice);

		textFieldSellingprice = new JTextField();
		textFieldSellingprice.setBounds(659, 80, 96, 19);
		PanelAddstock.add(textFieldSellingprice);
		textFieldSellingprice.setColumns(10);

		JLabel errorstockname = new JLabel("");
		errorstockname.setForeground(Color.RED);
		errorstockname.setBackground(new Color(222, 236, 255));
		errorstockname.setBounds(165, 57, 96, 13);
		PanelAddstock.add(errorstockname);

		JLabel errorbuyingdate = new JLabel("");
		errorbuyingdate.setForeground(Color.RED);
		errorbuyingdate.setBackground(new Color(222, 236, 255));
		errorbuyingdate.setBounds(419, 57, 96, 13);
		PanelAddstock.add(errorbuyingdate);

		JLabel errorsellingdate = new JLabel("");
		errorsellingdate.setForeground(Color.RED);
		errorsellingdate.setBackground(new Color(222, 236, 255));
		errorsellingdate.setBounds(659, 57, 96, 13);
		PanelAddstock.add(errorsellingdate);

		JLabel errorbuyingprice = new JLabel("");
		errorbuyingprice.setForeground(new Color(255, 0, 0));
		errorbuyingprice.setBackground(new Color(222, 236, 255));
		errorbuyingprice.setBounds(419, 101, 96, 13);
		PanelAddstock.add(errorbuyingprice);

		JLabel errorsellingprice = new JLabel("");
		errorsellingprice.setForeground(new Color(255, 0, 0));
		errorsellingprice.setBackground(new Color(222, 236, 255));
		errorsellingprice.setBounds(659, 101, 96, 13);
		PanelAddstock.add(errorsellingprice);

		JLabel lblStockQuantity = new JLabel("Stock Quantity:");
		lblStockQuantity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStockQuantity.setBounds(55, 79, 105, 19);
		PanelAddstock.add(lblStockQuantity);

		textFieldSTockQuantity = new JTextField();
		textFieldSTockQuantity.setBounds(165, 80, 96, 19);
		PanelAddstock.add(textFieldSTockQuantity);
		textFieldSTockQuantity.setColumns(10);

		JLabel errorstockquantity = new JLabel("");
		errorstockquantity.setForeground(Color.RED);
		errorstockquantity.setBounds(165, 101, 96, 13);
		PanelAddstock.add(errorstockquantity);

		JButton btnResetValues = new JButton("Reset Values");
		btnResetValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldStockName.setText(null);
				dateChooserBuyingdate.setDate(null);
				textFieldBuyingprice.setText(null);
				dateChooserSellingday.setDate(null);
				textFieldSellingprice.setText(null);
				textFieldSTockQuantity.setText(null);
				errorbuyingprice.setText(null);
				errorsellingprice.setText(null);
				errorstockname.setText(null);
				errorbuyingdate.setText(null);
				errorsellingdate.setText(null);
				errorstockquantity.setText(null);
			}
		});
		btnResetValues.setBackground(new Color(198, 207, 255));
		btnResetValues.setBounds(861, 32, 181, 31);
		PanelAddstock.add(btnResetValues);

		JButton btnSubmitValues = new JButton("Submit Values");
		btnSubmitValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String stockname = textFieldStockName.getText();
				String buyingdate = "";
				String sellingdate = "";
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				double buyingprice = 0;
				double sellingprice = 0;
				double stockquantity = 0;

				if (stockname.isEmpty()) {
					errorstockname.setText("Invalid input");
				}

				try {
					Date date1 = dateChooserBuyingdate.getDate();
					buyingdate = dateFormat.format(date1);
				} catch (Exception z) {
					errorbuyingdate.setText("Invalid input");
				}

				try {
					Date date2 = dateChooserSellingday.getDate();
					sellingdate = dateFormat.format(date2);
				} catch (Exception z) {
					errorsellingdate.setText("Invalid input");
				}

				try {
					buyingprice = Double.parseDouble(textFieldBuyingprice.getText());

				} catch (Exception z) {
					errorbuyingprice.setText("Invalid input");
				}

				try {
					sellingprice = Double.parseDouble(textFieldSellingprice.getText());
				} catch (Exception z) {
					errorsellingprice.setText("Invalid input");
				}

				try {
					stockquantity = Double.parseDouble(textFieldSTockQuantity.getText());
				} catch (Exception z) {
					errorstockquantity.setText("Invalid input");
				}

				if (!stockname.isEmpty() && buyingdate != null && sellingdate != null && buyingprice != 0
						&& sellingprice != 0 && stockquantity != 0) {
					double profitloss = sellingprice - buyingprice;

					insert_data(stockname, stockquantity, buyingdate, buyingprice, sellingdate, sellingprice,
							profitloss);

					textFieldStockName.setText(null);
					dateChooserBuyingdate.setDate(null);
					textFieldBuyingprice.setText(null);
					dateChooserSellingday.setDate(null);
					textFieldSellingprice.setText(null);
					textFieldSTockQuantity.setText(null);
					showtable();
				} else {
					JOptionPane.showMessageDialog(null, "Please enter Valid input");
				}

			}
		});
		btnSubmitValues.setBackground(new Color(198, 207, 255));
		btnSubmitValues.setBounds(861, 83, 181, 31);
		PanelAddstock.add(btnSubmitValues);

		JPanel PanelEditStock = new JPanel();
		layeredPane1.add(PanelEditStock, "name_259941810453800");
		PanelEditStock.setLayout(null);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerListModel(new String[] { "Stock Name", "Stock Quantity", "Buying Date",
				"Buying Price", "Selling Date", "Selling Price" }));
		spinner.setBounds(246, 90, 114, 20);
		PanelEditStock.add(spinner);

		JLabel lblEnterTheStockid = new JLabel("Enter the Stock_ID:");
		lblEnterTheStockid.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterTheStockid.setBounds(41, 40, 135, 31);
		PanelEditStock.add(lblEnterTheStockid);

		textFieldeditstockid = new JTextField();
		textFieldeditstockid.setBounds(246, 47, 114, 19);
		PanelEditStock.add(textFieldeditstockid);
		textFieldeditstockid.setColumns(10);

		JLabel lblWhatDoYou = new JLabel("What do you want to edit:");
		lblWhatDoYou.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWhatDoYou.setBounds(41, 83, 177, 31);
		PanelEditStock.add(lblWhatDoYou);

		JLabel labelselectedvalue = new JLabel("");
		labelselectedvalue.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelselectedvalue.setHorizontalAlignment(SwingConstants.RIGHT);
		labelselectedvalue.setBounds(674, 54, 153, 31);
		PanelEditStock.add(labelselectedvalue);

		JDateChooser dateChooserEditdate = new JDateChooser();
		dateChooserEditdate.setBounds(826, 66, 78, 19);
		PanelEditStock.add(dateChooserEditdate);
		dateChooserEditdate.setVisible(false);

		textFieldNewvalue = new JTextField();
		textFieldNewvalue.setBounds(860, 66, 96, 19);
		PanelEditStock.add(textFieldNewvalue);
		textFieldNewvalue.setColumns(10);
		textFieldNewvalue.setVisible(false);

		JButton btnSubmitAll = new JButton("Submit");
		btnSubmitAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int editstockid = Integer.valueOf(textFieldeditstockid.getText());
				String selectedoption = (String) spinner.getValue();
				Date dt = dateChooserEditdate.getDate();
				String value = textFieldNewvalue.getText();
				edit_data(editstockid,selectedoption,dt,value);
				showtable();
			}
		});
		btnSubmitAll.setBounds(783, 112, 85, 21);
		PanelEditStock.add(btnSubmitAll);
		btnSubmitAll.setVisible(false);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Integer.valueOf(textFieldeditstockid.getText());
				String val = (String) spinner.getValue();
				labelselectedvalue.setText(val + ":");

				if (val == "Buying Date" || val == "Selling Date") {
					dateChooserEditdate.setVisible(true);
					textFieldNewvalue.setVisible(false);
				} else {
					textFieldNewvalue.setVisible(true);
					dateChooserEditdate.setVisible(false);
				}
				btnSubmitAll.setVisible(true);
				}catch(Exception z) {
					JOptionPane.showMessageDialog(null, "Enter valid input");
				}

			}
		});
		btnNext.setBounds(437, 66, 85, 21);
		PanelEditStock.add(btnNext);

		JPanel PanelDeleteStock = new JPanel();
		PanelDeleteStock.setBackground(new Color(222, 236, 255));
		layeredPane1.add(PanelDeleteStock, "name_322398579850700");
		PanelDeleteStock.setLayout(null);

		JLabel lblEnterSrnoOf = new JLabel("Enter Stock name you wish to remove");
		lblEnterSrnoOf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterSrnoOf.setBounds(231, 50, 330, 24);
		PanelDeleteStock.add(lblEnterSrnoOf);

		textFieldRemoveStock = new JTextField();
		textFieldRemoveStock.setBounds(571, 55, 149, 19);
		PanelDeleteStock.add(textFieldRemoveStock);
		textFieldRemoveStock.setColumns(10);

		JButton btnRemoveStock = new JButton("Remove Stock");
		btnRemoveStock.setBackground(new Color(198, 207, 255));
		btnRemoveStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String removeval = textFieldRemoveStock.getText();
				remove_stock(removeval);
				showtable();
			}
		});
		btnRemoveStock.setBounds(450, 102, 181, 31);
		PanelDeleteStock.add(btnRemoveStock);

		JPanel PanelCreateCSV = new JPanel();
		PanelCreateCSV.setBackground(new Color(222, 236, 255));
		layeredPane1.add(PanelCreateCSV, "name_358210576387600");
		PanelCreateCSV.setLayout(null);

		JLabel lblEnterNameOf = new JLabel("Enter Name of CSV file:");
		lblEnterNameOf.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterNameOf.setBounds(291, 43, 182, 33);
		PanelCreateCSV.add(lblEnterNameOf);

		textFieldCSVfilename = new JTextField();
		textFieldCSVfilename.setBounds(493, 51, 157, 19);
		PanelCreateCSV.add(textFieldCSVfilename);
		textFieldCSVfilename.setColumns(10);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBackground(new Color(198, 207, 255));
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = textFieldCSVfilename.getText();
				create_csv(filename);
			}
		});
		btnGenerate.setBounds(407, 86, 181, 31);
		PanelCreateCSV.add(btnGenerate);

		JPanel PanelCalculator = new JPanel();
		layeredPane1.add(PanelCalculator, "name_369321218067000");
		PanelCalculator.setLayout(null);

		textFieldnum1 = new JTextField();
		textFieldnum1.setBounds(187, 28, 96, 19);
		PanelCalculator.add(textFieldnum1);
		textFieldnum1.setColumns(10);

		textFieldnum2 = new JTextField();
		textFieldnum2.setBounds(374, 28, 96, 19);
		PanelCalculator.add(textFieldnum2);
		textFieldnum2.setColumns(10);

		lblOperation.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperation.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOperation.setBounds(306, 17, 44, 30);
		PanelCalculator.add(lblOperation);

		JButton buttonadd = new JButton("+");
		buttonadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperation.setText("+");
			}
		});
		buttonadd.setBackground(new Color(198, 207, 255));
		buttonadd.setFont(new Font("Tahoma", Font.BOLD, 13));
		buttonadd.setBounds(239, 68, 44, 30);
		PanelCalculator.add(buttonadd);

		JButton buttonsub = new JButton("-");
		buttonsub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperation.setText("-");
			}
		});
		buttonsub.setBackground(new Color(198, 207, 255));
		buttonsub.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttonsub.setBounds(306, 67, 44, 30);
		PanelCalculator.add(buttonsub);

		JButton buttondiv = new JButton("/");
		buttondiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperation.setText("/");
			}
		});
		buttondiv.setBackground(new Color(198, 207, 255));
		buttondiv.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttondiv.setBounds(239, 108, 44, 30);
		PanelCalculator.add(buttondiv);

		JButton buttonmul = new JButton("*");
		buttonmul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperation.setText("*");
			}
		});
		buttonmul.setBackground(new Color(198, 207, 255));
		buttonmul.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttonmul.setBounds(374, 67, 44, 30);
		PanelCalculator.add(buttonmul);

		JButton buttonper = new JButton("%");
		buttonper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperation.setText("%");
			}
		});
		buttonper.setBackground(new Color(198, 207, 255));
		buttonper.setFont(new Font("Tahoma", Font.BOLD, 9));
		buttonper.setBounds(306, 110, 44, 30);
		PanelCalculator.add(buttonper);

		JLabel lblResult = new JLabel("Result");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblResult.setBounds(594, 36, 181, 59);
		PanelCalculator.add(lblResult);

		JButton buttonequals = new JButton("Calculate");
		buttonequals.setBackground(new Color(198, 207, 255));
		buttonequals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double num1 = Double.valueOf(textFieldnum1.getText());
					double num2 = Double.valueOf(textFieldnum2.getText());
					lblResult.setText(Double.toString(calculator(num1, num2)));
				} catch (Exception exp) {
					JOptionPane.showMessageDialog(null, "Enter valid input");
				}
			}
		});
		buttonequals.setBounds(596, 107, 181, 31);
		PanelCalculator.add(buttonequals);

		JButton btnC = new JButton("C");
		btnC.setBackground(new Color(198, 207, 255));
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperation.setText("?");
				textFieldnum1.setText("");
				textFieldnum2.setText("");
				lblResult.setText("Result");
			}
		});
		btnC.setBounds(374, 110, 44, 30);
		PanelCalculator.add(btnC);

		layeredPane2.setBounds(230, 186, 1072, 418);
		frame.getContentPane().add(layeredPane2);
		layeredPane2.setLayout(new CardLayout(0, 0));

		JPanel PanelTable = new JPanel();
		PanelTable.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		PanelTable.setBackground(new Color(222, 236, 255));
		layeredPane2.add(PanelTable, "name_67604667261800");
		PanelTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 65, 1052, 343);
		PanelTable.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);

		investmoney.setFont(new Font("Tahoma", Font.BOLD, 17));
		investmoney.setBounds(47, 21, 294, 34);
		PanelTable.add(investmoney);

		profitlossmoney.setFont(new Font("Tahoma", Font.BOLD, 17));
		profitlossmoney.setBounds(768, 18, 294, 40);
		PanelTable.add(profitlossmoney);
		showtable();

		JLabel labelIcon = new JLabel("");
		labelIcon.setBounds(60, 10, 112, 111);
		frame.getContentPane().add(labelIcon);
		labelIcon.setIcon(imgicon);

		JLabel lblStockManager = new JLabel("Stock Manager");
		lblStockManager.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
		lblStockManager.setBounds(60, 131, 141, 19);
		frame.getContentPane().add(lblStockManager);

		JButton btnCheckConnection = new JButton("Check Connection");
		btnCheckConnection.setHorizontalAlignment(SwingConstants.LEFT);
		btnCheckConnection.setBackground(new Color(198, 207, 255));
		btnCheckConnection.setIcon(imgcheck);
		btnCheckConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchlayeredPane1(PanelWelcome);
				Connection connection = check_connection();
				if (connection != null) {
					lblWelcomeToStock.setText("Connected!");
					lblWelcomeToStock.setIcon(imgok);
				} else {
					lblWelcomeToStock.setText("Not Connected!");
					lblWelcomeToStock.setIcon(imgnotok);
				}

			}
		});
		btnCheckConnection.setBounds(39, 186, 181, 31);
		frame.getContentPane().add(btnCheckConnection);

		JButton btnAddStock = new JButton("Add Stock");
		btnAddStock.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddStock.setIcon(imgadd);
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchlayeredPane1(PanelAddstock);
			}
		});
		btnAddStock.setBackground(new Color(198, 207, 255));
		btnAddStock.setBounds(39, 248, 181, 31);
		frame.getContentPane().add(btnAddStock);

		JButton btnEditStock = new JButton("Edit Stock");
		btnEditStock.setHorizontalAlignment(SwingConstants.LEFT);
		btnEditStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchlayeredPane1(PanelEditStock);

			}
		});
		btnEditStock.setBackground(new Color(198, 207, 255));
		btnEditStock.setIcon(imgedit);
		btnEditStock.setBounds(39, 310, 181, 31);
		frame.getContentPane().add(btnEditStock);

		JButton btnDeleteStock = new JButton("Delete Stock");
		btnDeleteStock.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteStock.setBackground(new Color(198, 207, 255));
		btnDeleteStock.setBounds(39, 367, 181, 31);
		btnDeleteStock.setIcon(imgdelete);
		btnDeleteStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchlayeredPane1(PanelDeleteStock);
			}
		});
		frame.getContentPane().add(btnDeleteStock);

		JButton btnCalculator = new JButton("Calculator");
		btnCalculator.setHorizontalAlignment(SwingConstants.LEFT);
		btnCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchlayeredPane1(PanelCalculator);
			}
		});
		btnCalculator.setBackground(new Color(198, 207, 255));
		btnCalculator.setIcon(imgcalcu);
		btnCalculator.setBounds(39, 425, 181, 31);
		frame.getContentPane().add(btnCalculator);

		JButton btnCreateCSV = new JButton("Convert to CSV");
		btnCreateCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchlayeredPane1(PanelCreateCSV);
			}
		});
		btnCreateCSV.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreateCSV.setBackground(new Color(198, 207, 255));
		btnCreateCSV.setIcon(imgprint);
		btnCreateCSV.setBounds(39, 484, 181, 31);
		frame.getContentPane().add(btnCreateCSV);

		JButton btnDeteteTable = new JButton("Delete Table");
		btnDeteteTable.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeteteTable.setBounds(39, 549, 181, 31);
		btnDeteteTable.setBackground(new Color(198, 207, 255));
		frame.getContentPane().add(btnDeteteTable);
		frame.setBounds(100, 100, 1340, 662);
		btnDeteteTable.setIcon(imgdeletetable);
		btnDeteteTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchlayeredPane1(PanelWelcome);
				delete_table();
				showtable();
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/// Methods

	public void switchlayeredPane1(JPanel panel) {
		layeredPane1.removeAll();
		layeredPane1.add(panel);
		layeredPane1.repaint();
		layeredPane1.revalidate();
	}

	public void showtable() {
		ResultSet rss = fetch_data();
		table.setModel(DbUtils.resultSetToTableModel(rss));
		String temp = get_investmoney();
		investmoney.setText("Invest Money: " + temp);

		double temp1 = get_profitlossmoney();
		String str = Double.toString(temp1);
		if (temp1 < 0) {
			profitlossmoney.setText("Loss Money: " + str);
			profitlossmoney.setForeground(new Color(255, 0, 0));

		} else {
			profitlossmoney.setText("Profit Money: " + str);
			profitlossmoney.setForeground(new Color(34, 139, 34));
		}

	}

	public double calculator(double num1, double num2) {

		String doit = lblOperation.getText();
		double result = 0;
		switch (doit) {

		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "/":
			result = num1 / num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "%":
			result = num1 % num2;
			break;
		default:
			JOptionPane.showMessageDialog(null, "Enter valid operation");
			break;
		}

		return result;
	}
}
