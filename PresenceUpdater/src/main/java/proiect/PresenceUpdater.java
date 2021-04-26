package proiect;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")
class DateLabelFormatter extends AbstractFormatter {
	private String datePattern = "yyyy-MM-dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}
}

public class PresenceUpdater {
	private static Sheets sheetsService;
	private static final List<String> SCOPES = Collections
			.singletonList(SheetsScopes.SPREADSHEETS);
	private static final JsonFactory JSON_FACTORY = JacksonFactory
			.getDefaultInstance();
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
	private static final String ICON_PATH = "/Icon.png";
	private static final String TOKENS_DIRECTORY_PATH = "tokens";
	private static final String APPLICATION_NAME = "PIP-pr";
	private static List<String> paths = new ArrayList<String>();
	private static JFrame frame = new JFrame();												// creeaza o fereastra JFrame
	private static JLabel label1 = new JLabel("Enter Google Sheets Link:");					// creeaza o eticheta JLabel
	private static JTextField textfield1 = new JTextField();								// creeaza un camp de text JTextField
	private static JButton button1 = new JButton("Next");									// creeaza un buton JButton
	private static JLabel label2 = new JLabel("Select Sheet:");								// creeaza o eticheta JLabel
	private static JComboBox<String> comboBox1 = new JComboBox<>();							// creeaza o caseta combo JCombo
	private static JButton button2 = new JButton("Next");									// creeaza un buton JButton
	private static JLabel label3 = new JLabel("Select Names Column:");						// creeaza o eticheta JLabel
	private static JComboBox<String> comboBox2 = new JComboBox<>();							// creeaza o caseta combo JCombo
	private static JLabel label4 = new JLabel("Enter Input Value:");						// creeaza o eticheta JLabel
	private static JTextField textfield2 = new JTextField();								// creeaza un camp de text JTextField
	private static JLabel label5 = new JLabel("Select Laboratories Columns and Dates:");	// creeaza o eticheta JLabel
	private static JComboBox<String> comboBox3 = new JComboBox<>();							// creeaza o caseta combo JCombo
	@SuppressWarnings("serial")
	private static Properties properties = new Properties() {{ put("text.today", "Today"); put("text.month", "Month"); put("text.year", "Year"); }};;
	
	private static UtilDateModel model1 = new UtilDateModel();
	private static JDatePickerImpl datePicker1 = new JDatePickerImpl(new JDatePanelImpl(
			model1, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox4 = new JComboBox<>();							// creeaza o caseta combo JCombo
	private static UtilDateModel model2 = new UtilDateModel();
	private static JDatePickerImpl datePicker2 = new JDatePickerImpl(new JDatePanelImpl(
			model2, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox5 = new JComboBox<>();							// creeaza o caseta combo JCombo
	private static UtilDateModel model3 = new UtilDateModel();
	private static JDatePickerImpl datePicker3 = new JDatePickerImpl(new JDatePanelImpl(
			model3, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox6 = new JComboBox<>();							// creeaza o caseta combo JCombo
	private static UtilDateModel model4 = new UtilDateModel();
	private static JDatePickerImpl datePicker4 = new JDatePickerImpl(new JDatePanelImpl(
			model4, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox7 = new JComboBox<>();							// creeaza o caseta combo JCombo
	private static UtilDateModel model5 = new UtilDateModel();
	private static JDatePickerImpl datePicker5 = new JDatePickerImpl(new JDatePanelImpl(
			model5, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox8 = new JComboBox<>();							// creeaza o caseta combo JCombo
	private static UtilDateModel model6 = new UtilDateModel();
	private static JDatePickerImpl datePicker6 = new JDatePickerImpl(new JDatePanelImpl(
			model6, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox9 = new JComboBox<>();							// creeaza o caseta combo JCombo
	private static UtilDateModel model7 = new UtilDateModel();
	private static JDatePickerImpl datePicker7 = new JDatePickerImpl(new JDatePanelImpl(
			model7, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox10 = new JComboBox<>();						// creeaza o caseta combo JCombo
	private static UtilDateModel model8 = new UtilDateModel();
	private static JDatePickerImpl datePicker8 = new JDatePickerImpl(new JDatePanelImpl(
			model8, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox11 = new JComboBox<>();						// creeaza o caseta combo JCombo
	private static UtilDateModel model9 = new UtilDateModel();
	private static JDatePickerImpl datePicker9 = new JDatePickerImpl(new JDatePanelImpl(
			model9, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox12 = new JComboBox<>();						// creeaza o caseta combo JCombo
	private static UtilDateModel model10 = new UtilDateModel();
	private static JDatePickerImpl datePicker10 = new JDatePickerImpl(new JDatePanelImpl(
			model10, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox13 = new JComboBox<>();						// creeaza o caseta combo JCombo
	private static UtilDateModel model11 = new UtilDateModel();
	private static JDatePickerImpl datePicker11 = new JDatePickerImpl(new JDatePanelImpl(
			model11, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox14 = new JComboBox<>();						// creeaza o caseta combo JCombo
	private static UtilDateModel model12 = new UtilDateModel();
	private static JDatePickerImpl datePicker12 = new JDatePickerImpl(new JDatePanelImpl(
			model12, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox15 = new JComboBox<>();						// creeaza o caseta combo JCombo
	private static UtilDateModel model13 = new UtilDateModel();
	private static JDatePickerImpl datePicker13 = new JDatePickerImpl(new JDatePanelImpl(
			model13, properties), new DateLabelFormatter());
	private static JButton button3 = new JButton("Next");									// creeaza un buton JButton									
	private static ArrayList<String> informations = new ArrayList<String>();
	private static JTextArea textarea = new JTextArea();
	private static JScrollPane scroll = new JScrollPane(textarea);
	@SuppressWarnings({ "rawtypes" })
	private static JComboBox[] comboBoxes = {comboBox3, comboBox4, comboBox5, comboBox6, comboBox7, comboBox8, comboBox9, comboBox10,
		comboBox11, comboBox12, comboBox13, comboBox14, comboBox15};
	private static JDatePickerImpl[] datePickers = {datePicker1, datePicker2, datePicker3, datePicker4, datePicker5, datePicker6, datePicker7, 
		datePicker8, datePicker9, datePicker10, datePicker11, datePicker12, datePicker13};



	private static Credential getCredentials() throws IOException,
	GeneralSecurityException {
		// Skip errors generated by google.
		final java.util.logging.Logger buggyLogger = java.util.logging.Logger
				.getLogger(FileDataStoreFactory.class.getName());
		buggyLogger.setLevel(java.util.logging.Level.SEVERE);

		// Load client secrets.
		InputStream in = PresenceUpdater.class
				.getResourceAsStream(CREDENTIALS_FILE_PATH);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
				JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY,
				clientSecrets, SCOPES)
		.setDataStoreFactory(
				new FileDataStoreFactory(new java.io.File(
						TOKENS_DIRECTORY_PATH)))
						.setAccessType("offline").build();

		Credential credential = new AuthorizationCodeInstalledApp(flow,
				new LocalServerReceiver()).authorize("user");

		return credential;
	}

	private static Sheets getSheetsService(Credential credential)
			throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.
		return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
				JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
				.build();
	}

	private static void updateGoogleSheet(String range, Sheets sheetsService,
			String sheetID, String sheetName, String value) throws Exception {
		try {
			ValueRange body = new ValueRange().setValues(Arrays.asList(Arrays
					.asList(value)));
			String sheetRange = sheetName + "!" + range;

			List <List<Object>> getValue = sheetsService.spreadsheets().values().get(sheetID, sheetRange).execute().getValues();
			if(getValue == null) {
				sheetsService.spreadsheets().values().update(sheetID, sheetRange, body).setValueInputOption("RAW").execute();
			}
			else {
				throw new Exception("Duplicate value!");
			}
		} catch (IOException e) {
			textarea.append("\nUpdate failed!");
		}
	}

	private static List<String> getPresenceLists(ArrayList<String> informations)
			throws IOException, GeneralSecurityException, ParseException {
		try {
			getCSVFiles();
		} catch (Exception NullPointerException) {
			textarea.append("Error opening CSV file!");
		}
		List<String> presenceList = new ArrayList<String>();
		List<String> presenceList2 = new ArrayList<String>();
		for (String path : paths) {
			try {
				File file = new File(path);
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis,
						StandardCharsets.UTF_8);
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(isr);
				String str;
				while ((str = reader.readLine()) != null) {
					if (str.contains("Date")) {
						String[] strSplit = str.replace("\"", "").split(", ");
						for (int i = 2; i < informations.size(); i++) {
							SimpleDateFormat formatter = new SimpleDateFormat(
									"yyyy-MM-dd");
							Date date = formatter.parse(strSplit[1]);
							Date dateMin = formatter
									.parse(informations.get(i)
											.substring(
													informations.get(i)
													.indexOf(".") + 2,
													informations.get(i)
													.length()));
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(dateMin);
							calendar.add(Calendar.DAY_OF_MONTH, 6);
							Date dateMax = calendar.getTime();
							if (!date.after(dateMax) && !date.before(dateMin)) {
								presenceList2.add(informations.get(i)
										.substring(
												0,
												informations.get(i)
												.indexOf(".") + 1));
								continue;
							}
						}
					}
					if (str.contains("\u2714")) {
						String[] strSplit = str.replace("\"", "").split(", ");
						String[] strSplitSplit = strSplit[0].split(" ");
						presenceList
						.add(presenceList2.get(presenceList2.size() - 1)
								+ strSplitSplit[1]
										+ " "
										+ strSplitSplit[0]);
					}
				}
			} catch (IOException e) {
				textarea.append("Error reading CSV file!");
			}
		}
		return presenceList;
	}

	private static void getCSVFiles() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView()
				.getHomeDirectory());
		jfc.setDialogTitle("Select CSV files");
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"CSV files", "csv");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File[] files = jfc.getSelectedFiles();
			Arrays.asList(files).forEach(x -> {
				if (x.isFile()) {
					paths.add(x.getPath().toString());
				}
			});
		}
	}

	private static List<List<Object>> getGoogleSheet(Sheets sheetsService,
			String sheetID, String sheetName) throws IOException,
			GeneralSecurityException {
		ValueRange result = sheetsService.spreadsheets().values()
				.get(sheetID, sheetName).execute();
		List<List<Object>> values = result.getValues();
		return values;
	}

	private static String findRange(String studentName,
			List<Object> studentsGoogleSheet, List<Object> headerGoogleSheet)
					throws IOException, GeneralSecurityException {
		int rangeNumber = 1;
		String range = null;
		for (Object student : studentsGoogleSheet.subList(1,
				studentsGoogleSheet.size())) {
			rangeNumber++;
			String[] studentNameSplit = student.toString().split(" ");
			String studentNameGoogleSheet = studentNameSplit[0] + " "
					+ studentNameSplit[2];
			String studentNamePresenceList = studentName.substring(
					studentName.indexOf(".") + 1, studentName.length());
			String laboratory = studentName.substring(0,
					studentName.indexOf("."));
			int rangeLetter = 64;
			for (Object letter : headerGoogleSheet) {
				rangeLetter++;
				if (letter.equals(laboratory)) {
					if (studentNameGoogleSheet.equals(studentNamePresenceList)) {
						range = String.valueOf((char) rangeLetter)
								+ String.valueOf(rangeNumber);
					}
				}
				continue;
			}
		}
		return range;
	}

	private static void firstInitializationGUI() throws IOException {
		/* Initializarea ferestrei JFrame */
		frame.setTitle("Presence Updater");									// seteaza titlul ferestrei	JFrame
		frame.setBounds(100, 100, 535, 650);								// seteaza limitele ferestrei JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// seteaza inchiderea ferestrei JFrame - inchidere aplicatie
		frame.setResizable(false);											// seteaza redimensionarea ferestrei JFrame - fara redimensionare
		frame.getContentPane().setLayout(null);								// seteaza amplasarea in cadrul ferestrei JFrame - fara amplasare
		frame.setIconImage(new ImageIcon(PresenceUpdater.class.getResource(ICON_PATH)).getImage());
		
		/* Initializarea primei etichete JLabel */
		label1.setBounds(150, 10, 215, 20);									// seteaza limitele primei etichete JLabel
		label1.setFont(new Font("Tahoma", Font.BOLD, 16));					// seteaza fontul textului din cadrul primei etichete JLabel
		frame.getContentPane().add(label1);									// adauga prima eticheta JLabel in cadrul ferestrei JFrame	

		/* Initializarea primului camp de text JTextField */
		textfield1.setBounds(10, 35, 495, 30);								// seteaza limitele primului campului de text JTextField 
		textfield1.setBackground(new Color(240,240,240));					// seteaza culoarea de fundal al primului camp de text JTextField 
		textfield1.setFont(new Font("Tahoma", Font.BOLD, 8));				// seteaza fontul textului din cadrul primului camp de text JTextField 
		textfield1.setForeground(Color.RED);								// seteaza culoarea textului din cadrul primului camp de text JTextField
		frame.getContentPane().add(textfield1);								// adauga primul campul de text JTextField in cadrul ferestrei JFrame

		/* Initializarea primului buton JButton */
		button1.setBounds(405, 545, 100, 35);								// seteaza limitele butonului JButton
		button1.setFont(new Font("Tahoma", Font.BOLD, 16));					// seteaza fontul textului din cadrul butonului JButton
		frame.getContentPane().add(button1);								// adauga butonul JButton in cadrul ferestrei JFrame
	}

	private static void secondInitializationGUI() {
		textfield1.setEditable(false);
		frame.getContentPane().remove(button1);

		/* Initializarea celei de-a doua etichete JLabel */
		label2.setBounds(10, 65, 110, 30);									// seteaza limitele celei de-a doua etichete JLabel
		label2.setFont(new Font("Tahoma", Font.BOLD, 16));					// seteaza fontul textului din cadrul celei de-a doua etichete JLabel
		frame.getContentPane().add(label2);									// adauga a doua eticheta JLabel in cadrul ferestrei JFrame

		/* Initializarea primei casete combo JComboBox */
		comboBox1.setBounds(10, 95, 100, 25);
		comboBox1.setSelectedIndex(0);
		comboBox1.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox1.setForeground(Color.RED);
		frame.add(comboBox1);

		/* Initializarea celui de-al doilea buton JButton */
		button2.setBounds(405, 545, 100, 35);								// seteaza limitele butonului JButton
		button2.setFont(new Font("Tahoma", Font.BOLD, 16));					// seteaza fontul textului din cadrul butonului JButton
		frame.getContentPane().add(button2);								// adauga butonul JButton in cadrul ferestrei JFrame
	}

	@SuppressWarnings("rawtypes")
	private static void thirdInitializationGUI() {
		frame.getContentPane().remove(button2);
		comboBox1.setEnabled(false);

		/* Initializarea celei de-a treia etichete JLabel */
		label3.setBounds(150, 65, 190, 30);									// seteaza limitele celei de-a treia etichete JLabel
		label3.setFont(new Font("Tahoma", Font.BOLD, 16));					// seteaza fontul textului din cadrul celei de-a treia etichete JLabel
		frame.getContentPane().add(label3);									// adauga a treia eticheta JLabel in cadrul ferestrei JFrame

		/* Initializarea celei de-a doua casete combo JComboBox */
		comboBox2.setBounds(200, 95, 90, 25);
		comboBox2.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox2.setForeground(Color.RED);
		frame.add(comboBox2);

		/* Initializarea celei de-a patra etichete JLabel */				
		label4.setBounds(355, 65, 150, 30);									
		label4.setFont(new Font("Tahoma", Font.BOLD, 16));					
		frame.getContentPane().add(label4);									

		/* Initializarea celui de-al doilea camp de text JTextField */							
		textfield2.setBounds(380, 95, 92, 25);								
		textfield2.setBackground(new Color(240,240,240));	
		textfield2.setFont(new Font("Tahoma", Font.BOLD, 14));	
		textfield2.setForeground(Color.RED);
		textfield2.setText("P");
		frame.getContentPane().add(textfield2);								

		/* Initializarea celei de-a cincea etichete JLabel */	
		label5.setBounds(90, 120, 330, 30);										
		label5.setFont(new Font("Tahoma", Font.BOLD, 16));						
		frame.getContentPane().add(label5);										

		/* Initializarea celor 13 casete combo JComboBox */
		int y = 150;
		for (JComboBox comboBox : comboBoxes) {
			comboBox.setBounds(150, y, 90, 25);
			y = y + 30;
			comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
			comboBox.setForeground(Color.RED);
			frame.add(comboBox);
		}

		/* Initializarea celor 13 casete de date JDatePick */
		y = 150;
		for (JDatePickerImpl datePicker : datePickers) {
			datePicker.setBounds(260, y, 110, 30);
			y = y + 30;
			frame.add(datePicker);	
		}

		/* Initializarea celui de-al doilea buton JButton */
		button3.setBounds(405, 545, 100, 35);									// seteaza limitele butonului JButton
		button3.setFont(new Font("Tahoma", Font.BOLD, 16));						// seteaza fontul textului din cadrul butonului JButton
		frame.getContentPane().add(button3);									// adauga butonul JButton in cadrul ferestrei JFrame
	}

	@SuppressWarnings("rawtypes")
	private static void fourthInitializationGUI() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			informations.add(comboBox2.getSelectedItem().toString());
		}
		catch (NullPointerException e) {
			informations.add("");
		}
		informations.add(textfield2.getText());
		for (JComboBox comboBox : comboBoxes) {
			try {
				informations.add(comboBox.getSelectedItem().toString());
			}
			catch (NullPointerException e) {
				informations.add("");
			}

		}
		int i = 2;
		for (JDatePickerImpl datePicker : datePickers) {
			informations.set(i, informations.get(i) + ". " + formatter.format(datePicker.getModel().getValue()));
			i++;
		}

		frame.getContentPane().removeAll();

		/* Initializarea zonei de text JTextArea */							
		textarea.setFont(new Font("Tahoma", Font.BOLD, 14));
		textarea.setBackground(new Color(240,240,240));	
		textarea.setText("0 data updated!");
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 10, 500, 580);
		frame.getContentPane().add(scroll);
	}


	public static void main(String[] args) throws IOException,
	GeneralSecurityException, ParseException {
		firstInitializationGUI();
		frame.setVisible(true);

		/* Action Listener Interface - interfata executa la primirea evenimentelor */ 
		button1.addActionListener(new ActionListener() {
			/* Action Perfomed Method - functia executata la apasara butonului */
			public void actionPerformed(ActionEvent arg0) {
				try {													// codul bloc executat daca nu exista erori
					Credential credential = getCredentials();
					sheetsService = getSheetsService(credential);

					String sheetID = textfield1.getText().substring(
							textfield1.getText().indexOf("/d/") + 3,
							textfield1.getText().lastIndexOf("/"));

					Spreadsheet response1 = sheetsService.spreadsheets().get(sheetID)
							.setIncludeGridData(false).execute();

					List<Sheet> workSheetList = response1.getSheets();

					List<String> sheetsNames = new ArrayList<String>(); 

					for (Sheet sheet : workSheetList) {
						sheetsNames.add(sheet.getProperties().getTitle());
					}
					String[] array1 = new String[workSheetList.size()];
					sheetsNames.toArray(array1); 	

					for (String arrayValue : array1) {
						if(!arrayValue.equals("")) {
							comboBox1.addItem(arrayValue);
						}
					}

					secondInitializationGUI();
					SwingUtilities.updateComponentTreeUI(frame);

					/* Action Listener Interface - interfata executa la primirea evenimentelor */ 
					button2.addActionListener(new ActionListener() {
						/* Action Perfomed Method - functia executata la apasara butonului */
						@SuppressWarnings({ "unchecked", "rawtypes" })
						public void actionPerformed(ActionEvent arg0) {
							try {													// codul bloc executat daca nu exista erori

								String sheetName = comboBox1.getSelectedItem().toString();
								List<List<Object>> googleSheet = getGoogleSheet(sheetsService, sheetID,
										sheetName);


								List<Object> headerGoogleSheet = googleSheet.get(0);

								String[] array2 = new String[headerGoogleSheet.size()];
								headerGoogleSheet.toArray(array2); 

								UtilDateModel[] models = {model1, model2, model3, model4, model5, model6, model7, model8, model9, model10, model11, model12, model13};
								for(UtilDateModel model : models) {
									model.setSelected(true);
								}
								model1.setDate(2021, 2, 1);
								model2.setDate(2021, 2, 8);
								model3.setDate(2021, 2, 15);
								model4.setDate(2021, 2, 22);
								model5.setDate(2021, 2, 29);
								model6.setDate(2021, 3, 5);
								model7.setDate(2021, 3, 12);
								model8.setDate(2021, 3, 19);
								model9.setDate(2021, 3, 26);
								model10.setDate(2021, 4, 10);
								model11.setDate(2021, 4, 17);
								model12.setDate(2021, 4, 24);
								model13.setDate(2021, 4, 31);
								try{
									for (String arrayValue : array2) {
										if(!arrayValue.equals("")) {
											comboBox2.addItem(arrayValue);
											for (JComboBox comboBox : comboBoxes) {
												comboBox.addItem(arrayValue);
											}
										}
									}

									comboBox2.setSelectedIndex(1);
									int i = 4;
									for (JComboBox comboBox : comboBoxes) {
										comboBox.setSelectedIndex(i);
										i++;
									}
								} catch (IllegalArgumentException e) {
									comboBox2.setSelectedIndex(-1);
									for (JComboBox comboBox : comboBoxes) {
										comboBox.setSelectedIndex(-1);
									}
								}

								thirdInitializationGUI();
								SwingUtilities.updateComponentTreeUI(frame);

								/* Action Listener Interface - interfata executa la primirea evenimentelor */ 
								button3.addActionListener(new ActionListener() {
									/* Action Perfomed Method - functia executata la apasara butonului */
									public void actionPerformed(ActionEvent arg0) {
										try {													// codul bloc executat daca nu exista erori

											fourthInitializationGUI();
											SwingUtilities.updateComponentTreeUI(frame);

											List<String> presenceLists = getPresenceLists(informations);

											int collumnNumber = 0;
											for (Object collumn : headerGoogleSheet) {
												collumnNumber++;
												if (collumn.equals(informations.get(0))) {
													break;
												}
											}

											List<Object> studentsGoogleSheet = new ArrayList<Object>();

											try {
												for (List<?> student : googleSheet) {

													studentsGoogleSheet.add(student.get(collumnNumber - 1));
												}
											} catch (IndexOutOfBoundsException e) {
											}

											int i = 0;
											for (String studentName : presenceLists) {

												String range = findRange(studentName, studentsGoogleSheet,
														headerGoogleSheet);
												if (range != null) {
													try {
														updateGoogleSheet(range, sheetsService, sheetID, sheetName, informations.get(1));
														i++;
														textarea.setText(i + textarea.getText().substring(textarea.getText().indexOf("data updated")-1, textarea.getText().length() ));
														textarea.update(textarea.getGraphics());
														TimeUnit.SECONDS.sleep(1);
													} catch (Exception e) {
														textarea.append("\nDuplicate presence: "
																+ studentName.substring(studentName.indexOf(".") + 1,
																		studentName.length()) + " (" + studentName.substring(0, studentName.indexOf(".")) + ")");
													}
												} else {
													if(!studentName.substring(0, studentName.indexOf(".")).equals("")) {
														textarea.append("\nCouldn't find "
																+ studentName.substring(studentName.indexOf(".") + 1,
																		studentName.length()) + " in Google Sheets! (" + studentName.substring(0, studentName.indexOf(".")) + ")");
													}
												}
											}
											textarea.append("\nClose application pressing the X button!");
										} catch (GeneralSecurityException | IOException | ParseException e) {
											textarea.append("\nAn error has occurred. Please restart application!");
										}
									}
								});

							}
							catch (GeneralSecurityException | IOException e) {
								textarea.append("\nAn error has occurred. Please restart application!");
							}
						}
					});
				} catch (GeneralSecurityException | IOException e) {									// codul bloc executat daca exista erori	
					textarea.append("\nAn error has occurred. Please restart application!");
				}
			}
		});
	}
}