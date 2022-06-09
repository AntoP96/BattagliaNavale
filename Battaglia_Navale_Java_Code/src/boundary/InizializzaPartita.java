package boundary;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import control.GestorePartite;
import java.awt.Toolkit;

public class InizializzaPartita implements DocumentListener {

	static JFrame frmInizializzaPartita;
	static JPanel contentPane;
	private JTextField txtGiocatore1;
	private JTextField txtGiocatore2;
	private JTextField txtGiocatore3;
	private JTextField txtNumTentativiMax;
	private JTextField txtNumRighe;
	private JTextField txtNumColonne;
	private JTextField txtRigaBersaglio;
	private JTextField txtColonnaBersaglio;
	private JButton confermaInserimento;
	private ArrayList<JTextField> textFields = new ArrayList<>();
	private ArrayList<JPanel> listaButton=new ArrayList<>();
	static JPanel panelInizializza;
	private ImageIcon scoppio;
	private JLabel labelScoppio;
	private ImageIcon mancato;
	private JLabel labelMancato;

	static GestorePartite gestore = new GestorePartite();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InizializzaPartita window = new InizializzaPartita();
					window.frmInizializzaPartita.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InizializzaPartita() {
		initialize();
	}
	
	public void aggiungiScoppio(int numRiga, int numColonna) {
		listaButton.get(numRiga*((int)Math.sqrt(listaButton.size()))+(numColonna-1)).add(labelScoppio);
	}
	
	public void aggiungiMancato(int numRiga, int numColonna) {
		listaButton.get(numRiga*((int)Math.sqrt(listaButton.size()))+(numColonna-1)).add(labelMancato);
	}
	
	public void rimuoviMancato(int numRiga, int numColonna) {
		listaButton.get(numRiga*((int)Math.sqrt(listaButton.size()))+(numColonna-1)).remove(labelMancato);
	}
	
	public void addTextField(JTextField textField) {
		textFields.add(textField);
		textField.getDocument().addDocumentListener(this);
	}
	public void clearTextFields() {
		for(JTextField textField: textFields)
			textField.setText("");
	}
	public boolean isDataEntered() {
		for (JTextField textField : textFields) {
			if (textField.getText().trim().length() == 0)
				return false;
		}

		return true;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		checkData();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkData();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	private void checkData() {
		confermaInserimento.setEnabled(isDataEntered());
	}
	public void coloraBottoni(int numRiga, int numColonna, Color color) {

		listaButton.get(numRiga * ((int) Math.sqrt(listaButton.size())) + (numColonna - 1)).setBackground(color);

	}
	
	public int checkInputFields() {
		int righe = 0, colonne = 0;

		String giocatore1 = txtGiocatore1.getText();
		String giocatore2="";
		String giocatore3="";
		int numGiocatori = 1;
		int numTentativiMax;
		int rigaBersaglio;
		int colonnaBersaglio;
		if (txtNumTentativiMax.getText().matches("^\\d+$") && txtRigaBersaglio.getText().matches("^\\d+$")
				&& txtColonnaBersaglio.getText().matches("^\\d+$") && txtNumRighe.getText().matches("^\\d{1}$")
				&& txtNumColonne.getText().matches("^\\d{1}$")) {
			numTentativiMax = Integer.parseInt(txtNumTentativiMax.getText());
			rigaBersaglio = Integer.parseInt(txtRigaBersaglio.getText());
			colonnaBersaglio = Integer.parseInt(txtColonnaBersaglio.getText());
			righe = Integer.parseInt(txtNumRighe.getText());
			colonne = Integer.parseInt(txtNumColonne.getText());
		} else {
			return 5;
		}
		if (!txtGiocatore2.getText().isEmpty()) {
			giocatore2 = txtGiocatore2.getText();
			numGiocatori += 1;

		}
		if (!txtGiocatore3.getText().isEmpty()) {
			giocatore3 = txtGiocatore3.getText();
			numGiocatori += 1;
		}
		if (!(giocatore2.isEmpty() || giocatore3.isEmpty()) && (giocatore1.equals(giocatore2) || giocatore1.equals(giocatore3) || giocatore2.equals(giocatore3))) {
			return 1;
		}

		if (righe != colonne)
			return 2;

		if (rigaBersaglio > righe || colonnaBersaglio > colonne)
			return 3;

		if (!(numTentativiMax % numGiocatori == 0))
			return 4;
		return 0;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInizializzaPartita = new JFrame();
		frmInizializzaPartita.setResizable(false);
		frmInizializzaPartita.setIconImage(
		Toolkit.getDefaultToolkit().getImage(InizializzaPartita.class.getResource("/boundary/icona.png")));
		frmInizializzaPartita.setTitle("Inizializza Partita");
		frmInizializzaPartita.setBounds(100, 100, 450, 300);
		frmInizializzaPartita.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frameInizializza=new JFrame();

		JPanel panelInizializza = new JPanel();
		panelInizializza.setBackground(new Color(255, 255, 204));
		panelInizializza.setBounds(0, 10, 436, 172);
		frmInizializzaPartita.getContentPane().add(panelInizializza);
		panelInizializza.setLayout(null);

		txtGiocatore1 = new JTextField();

		txtGiocatore1.setColumns(10);
		txtGiocatore1.setBounds(10, 29, 96, 19);
		panelInizializza.add(txtGiocatore1);
		textFields.add(txtGiocatore1);
		txtGiocatore1.getDocument().addDocumentListener(this);

		txtGiocatore2 = new JTextField();
		txtGiocatore2.setColumns(10);
		txtGiocatore2.setBounds(146, 29, 96, 19);
		panelInizializza.add(txtGiocatore2);

		txtGiocatore3 = new JTextField();
		txtGiocatore3.setColumns(10);
		txtGiocatore3.setBounds(278, 29, 96, 19);
		panelInizializza.add(txtGiocatore3);

		txtNumTentativiMax = new JTextField();
		txtNumTentativiMax.setColumns(10);
		txtNumTentativiMax.setBounds(10, 80, 51, 19);
		panelInizializza.add(txtNumTentativiMax);
		textFields.add(txtNumTentativiMax);
		txtNumTentativiMax.getDocument().addDocumentListener(this);

		txtNumRighe = new JTextField();
		txtNumRighe.setColumns(10);
		txtNumRighe.setBounds(166, 80, 51, 19);
		panelInizializza.add(txtNumRighe);
		textFields.add(txtNumRighe);
		txtNumRighe.getDocument().addDocumentListener(this);

		txtNumColonne = new JTextField();
		txtNumColonne.setColumns(10);
		txtNumColonne.setBounds(291, 80, 51, 19);
		panelInizializza.add(txtNumColonne);
		textFields.add(txtNumColonne);
		txtNumColonne.getDocument().addDocumentListener(this);

		txtRigaBersaglio = new JTextField();
		txtRigaBersaglio.setColumns(10);
		txtRigaBersaglio.setBounds(10, 135, 51, 19);
		panelInizializza.add(txtRigaBersaglio);
		textFields.add(txtRigaBersaglio);
		txtRigaBersaglio.getDocument().addDocumentListener(this);

		txtColonnaBersaglio = new JTextField();
		txtColonnaBersaglio.setColumns(10);
		txtColonnaBersaglio.setBounds(146, 135, 51, 19);
		panelInizializza.add(txtColonnaBersaglio);
		textFields.add(txtColonnaBersaglio);
		txtColonnaBersaglio.getDocument().addDocumentListener(this);

		confermaInserimento = new JButton("Conferma");
		confermaInserimento.setEnabled(false);
		confermaInserimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int righe = 0, colonne = 0;
				ArrayList<String> listaGiocatori = new ArrayList<>();
				String giocatore1;
				String giocatore2 = "";
				String giocatore3 = "";
				int numTentativiMax;
				int rigaBersaglio;
				int colonnaBersaglio;

				giocatore1 = txtGiocatore1.getText();
				int i = checkInputFields();
				/*SWITCH CASE PER ERRORI:
				 * 0: input validi, creazione partita
				 * 1: Errore sul nome dei giocatori: Non è possibile inserire due giocatori con lo stesso nome!
				 * 2: Errore sulle dimensioni della griglia: La griglia deve essere quadrata!
				 * 3: Errore sulla posizione del bersaglio: Il bersaglio deve essere contenuto nella griglia
				 * 4: Errore sul numero dei tentativi massimo inserito: Il numero deve essere un multiplo del numero di giocatori
				 * 5: Errore sul formato degli input inseriti dall'Amministratore.
				*/
				switch (i) {
				case 0:
					if (!txtGiocatore2.getText().isEmpty()) {
						giocatore2 = txtGiocatore2.getText();
						listaGiocatori.add(giocatore2);
					}
					if (!txtGiocatore3.getText().isEmpty()) {
						giocatore3 = txtGiocatore3.getText();
						listaGiocatori.add(giocatore3);
					}
					listaGiocatori.add(giocatore1);

					numTentativiMax = Integer.parseInt(txtNumTentativiMax.getText());
					rigaBersaglio = Integer.parseInt(txtRigaBersaglio.getText());
					colonnaBersaglio = Integer.parseInt(txtColonnaBersaglio.getText());

					righe = Integer.parseInt(txtNumRighe.getText());
					colonne = Integer.parseInt(txtNumColonne.getText());
					BattagliaNavale b = new BattagliaNavale();
					JPanel[][] grid;
					grid = new JPanel[righe][colonne];
					b.panelGriglia.setLayout(new GridLayout(righe, colonne));
					for (int y = 0; y < righe; y++) {
						for (int x = 0; x < colonne; x++) {
							grid[x][y] = new JPanel();
							grid[x][y].setEnabled(false);
							grid[x][y].setBackground(Color.white);
							grid[x][y].setBorder(new LineBorder(Color.black));
							b.panelGriglia.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
							b.panelGriglia.add(grid[x][y]);
							listaButton.add(grid[x][y]);
							
						}
					}
					
					b.frmBattagliaNavale.setVisible(true);
					
					
					frmInizializzaPartita.setVisible(false);
					clearTextFields();
					txtGiocatore2.setText("");
					txtGiocatore3.setText("");
					gestore.inizializzaPartita(listaGiocatori, numTentativiMax, righe, colonne, rigaBersaglio-1,
							colonnaBersaglio-1);
					b.aggiornaListaGiocatori(gestore.restituisciNomeGiocatori());
					b.gestisciPartita();
					break;
				case 1: 
					JOptionPane.showMessageDialog(null, "Il nome dei giocatori deve essere univoco!!!", "ERRORE!!!", JOptionPane.ERROR_MESSAGE);
					clearTextFields();
					JOptionPane.showMessageDialog(null, "Inserisci di nuovo i dati");
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "La griglia deve essere quadrata!!!", "ERRORE!!!", JOptionPane.ERROR_MESSAGE);
					clearTextFields();
					JOptionPane.showMessageDialog(null, "Inserisci di nuovo i dati");
					break;
				
				case 3:
					JOptionPane.showMessageDialog(null, "Il bersaglio deve essere posizionato all'interno della griglia!!!", "ERRORE!!!", JOptionPane.ERROR_MESSAGE);
					clearTextFields();
					JOptionPane.showMessageDialog(null, "Inserisci di nuovo i dati");
					break;
					
				case 4:
					JOptionPane.showMessageDialog(null, "Il numero di tentativi massimo deve essere un multiplo del numero di giocatori!!!", "ERRORE!!!", JOptionPane.ERROR_MESSAGE);
					clearTextFields();
					JOptionPane.showMessageDialog(null, "Inserisci di nuovo i dati");
					break;
				
				case 5:
					JOptionPane.showMessageDialog(null, "Errore nel formato dei dati!!!", "ERRORE!!!", JOptionPane.ERROR_MESSAGE);
					clearTextFields();
					JOptionPane.showMessageDialog(null, "Inserisci di nuovo i dati");
					break;
				}

			
			}

		});
		confermaInserimento.setBounds(291, 134, 135, 21);
		panelInizializza.add(confermaInserimento);

		JLabel lblNewLabel = new JLabel("Nome Giocatore 1");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 10, 105, 13);
		panelInizializza.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome Giocatore 2");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(148, 10, 105, 13);
		panelInizializza.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nome Giocatore 3");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(278, 10, 105, 13);
		panelInizializza.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Numero Tentativi \r\nMassimo");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(10, 57, 160, 21);
		panelInizializza.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Numero righe griglia");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(166, 57, 112, 21);
		panelInizializza.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Numero colonne griglia");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_5.setBounds(288, 57, 125, 19);
		panelInizializza.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Posizione riga bersaglio");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_6.setBounds(10, 112, 126, 19);
		panelInizializza.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Posizione colonna bersaglio");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 10));

		lblNewLabel_7.setBounds(143, 112, 146, 19);
		panelInizializza.add(lblNewLabel_7);
		
		scoppio = new ImageIcon(this.getClass().getResource("/boundary/scoppio.png"));
		labelScoppio = new JLabel(scoppio);
		
		mancato = new ImageIcon(this.getClass().getResource("/boundary/mancato.png"));
		labelMancato = new JLabel(mancato);

	}

}
