/**Questa classe rappresenta l'interfaccia grafica principale dell'applicazione e permette la creazione di una nuova partita o le interrogazioni
 * ,da parte dei Giocatori o dell'Amministratore, previste nella traccia( non sono state implementate perchè non richieste).
 * Questa classe dichiara i suoi attributi frmBattagliaNavale e panelGriglia come public in modo da permettere alla classe InizializzaPartita di
 * creare a runtime, sul JPanel di questa classe , la griglia dove verrà svolta la partita. Inoltre questa classe si occupa di gestire la ciclicità
 * dei turni durante la partita e richiama i diversi Giocatori attraverso dei confirmDialog per permettergli di inserire i dati relativi alla mossa
 * da effettuare e sfruttando i metodi del package sottostante forniti dalla classe GestorePartite, fornisce diversi output in base ai valori di 
 * ritorno ottenuti.
 * 
 */
package boundary;

import java.awt.EventQueue;
import control.GestorePartite;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import boundary.BattagliaNavale.StatusColumnCellRenderer;

import java.awt.Toolkit;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BattagliaNavale {
	/**frmBattagliaNavale: JFrame che incapsula i JPanel da mostrare al giocatore
	 * 
	 */
	public  JFrame frmBattagliaNavale;
	/**panelGriglia: JPanel con GridLayout su cui, all'inizializzazione di una nuova partita, viene creata la griglia di JPanel che fungerà da griglia
	 * per il gioco BattagliaNavale.
	 * 
	 */
	public JPanel panelGriglia;
	/**frameInizializza: riferimento alla classe InizalizzaPartita utilizzato per mostrare all'utente il form di InizializzaPartita quando egli
	 * preme sul JButton inizializzaPartita.
	 * 
	 */
	private static InizializzaPartita frameInizializza;
	
	/**tabellaGiocatori: JTable utilizzata per mostrare i dati relativi ai giocatori in gara: il loro nome e il colore del segnaposto.
	 * Questa JTable viene mostrata sul JPanel panelMenu.
	 * 
	 */
	private final JTable tabellaGiocatori = new JTable();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattagliaNavale window = new BattagliaNavale();
					window.frmBattagliaNavale.setVisible(true);
					frameInizializza = new InizializzaPartita();
					frameInizializza.frmInizializzaPartita.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**Classe utilizzata per aggiungere un TableCellRender custom alla JTable tabella giocatori.
	 * In particolare questa classe si occupa di colorare la colonna accanto al nome dei giocatori per indicare il loro segnaposto.
	 * Sfrutta il metodo della classe GestorePartite per ottenere il colore random assegnato in fase di creazione ad ogni giocatore in gara
	 * e lo inserisce nella cella associata al giocatore di cui si è ottenuto il colore.
	 * 
	 * 
	 *
	 */
	public class StatusColumnCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			// Cells are by default rendered as a JLabel.
			JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

			l.setBackground(
					InizializzaPartita.gestore.getColorFromGiocatore(table.getValueAt(row, col - 1).toString()));

			// Return the JLabel which renders the cell.
			return l;

		}
	}

	/**
	 * Create the application.
	 */
	public BattagliaNavale() {
		initialize();
	}
	/**Metodo che aggiorna la prima colonna della JTable inserendo i nomi dei giocatori in gara e inoltre setta il custom TableCellRenderer sulla 
	 * seconda colonna della tabella. Tale metodo viene richiamato dalla classe InizializzaPartita quando l'Amministratore ha inserito correttamente
	 * i dati.
	 * 
	 * @param listaGiocatori : lista dei giocatori in gara
	 */
	public void aggiornaListaGiocatori(ArrayList<String> listaGiocatori) {
		int i = 0;
		for (String giocatore : listaGiocatori) {
			this.tabellaGiocatori.getModel().setValueAt(giocatore, i, 0);

			i++;
		}
		this.tabellaGiocatori.getColumnModel().getColumn(1).setCellRenderer(new StatusColumnCellRenderer());
	}

	/**Metodo che si occupa della gestione di una partita e quindi crea due cicli, uno per partita e uno per turno, dentro i quali risiede la logica
	 * per richiamare il giocatore ad effettuare una mossa: mostrando un confirmDialog con due JTextField e un JButton di conferma.
	 * Successivamente si invoca il metodo di GestorePartite effettuaMossa(), il quale restituisce una stringa di controllo.
	 *  Local variable: RISULTATO ha quattro possibili valori:
	 * -Nome Giocatore -> stampo il vincitore 
	 * -Mossa non valida -> errore e tocca di nuovo a quel giocatore 
	 * -Tentativi esauriti -> errore e passa il turno (se finiscono tutti i tentativi -> la partita termina senza un vincitore.
	 * -Termine turno -> passa il turno al prossimo giocatore
	 * Inoltre questo metodo si occupa di colorare i JPanel e aggiungere delle icone per mostrare ai Giocatori la posizione del loro segnaposto
	 * e l'esito della loro mossa.
	 */
	public void gestisciPartita() {
		JTextField numRiga = new JTextField();
		JTextField numColonna = new JTextField();
		Object[] message = { "Numero riga:", numRiga, "Numero colonna:", numColonna, };
		int j = 0, numTurniTerminati = 0;
		String risultato = "";
		String nomeGiocatore = "";
		loopPartita: while (true) {

			loopTurno: for (int i = 0; i < InizializzaPartita.gestore.restituisciNomeGiocatori().size(); i++) {

				int option = JOptionPane.showConfirmDialog(null, message,
						InizializzaPartita.gestore.restituisciNomeGiocatori().get(i) + " e' il tuo turno!",
						JOptionPane.DEFAULT_OPTION);
				if (option == JOptionPane.OK_OPTION && numRiga.getText().matches("^\\d+$")
						&& numColonna.getText().matches("^\\d+$")) {

					nomeGiocatore = InizializzaPartita.gestore.restituisciNomeGiocatori().get(i);
					risultato = InizializzaPartita.gestore.effettuaMossa(nomeGiocatore, j,
							Integer.parseInt(numRiga.getText()) - 1, Integer.parseInt(numColonna.getText()) - 1);

					if (risultato.equals(nomeGiocatore)) {
						frameInizializza.coloraPanel(Integer.parseInt(numRiga.getText()) - 1,
								Integer.parseInt(numColonna.getText()),
								InizializzaPartita.gestore.getColorFromGiocatore(nomeGiocatore));
						frameInizializza.aggiungiScoppio(Integer.parseInt(numRiga.getText()) - 1,
								Integer.parseInt(numColonna.getText()));
						this.frmBattagliaNavale.revalidate();
						this.frmBattagliaNavale.repaint();
						JOptionPane.showMessageDialog(null, "Il vincitore della partita e': "
								+ InizializzaPartita.gestore.restituisciNomeGiocatori().get(i));

						break loopPartita;
					} else if (risultato.equals("Tentativi esauriti")) {
							JOptionPane.showMessageDialog(null,
							InizializzaPartita.gestore.restituisciNomeGiocatori().get(i) + ": " + risultato);
						
							if (i== InizializzaPartita.gestore.restituisciNomeGiocatori().size()-1) {
							JOptionPane.showMessageDialog(null, "La partita si e' conclusa senza un vincitore!");
							break loopPartita;
							} 
							
					} else if (risultato.equals("Mossa non valida")) {
						JOptionPane.showMessageDialog(null,
								InizializzaPartita.gestore.restituisciNomeGiocatori().get(i) + ": " + risultato);
						i--;
						numRiga.setText("1");
						numColonna.setText("1");
					} else {
						frameInizializza.coloraPanel(Integer.parseInt(numRiga.getText()) - 1,
								Integer.parseInt(numColonna.getText()),
								InizializzaPartita.gestore.getColorFromGiocatore(nomeGiocatore));
						frameInizializza.aggiungiMancato(Integer.parseInt(numRiga.getText()) - 1,
								Integer.parseInt(numColonna.getText()));
						this.frmBattagliaNavale.revalidate();
						this.frmBattagliaNavale.repaint();
						JOptionPane.showMessageDialog(null,
								InizializzaPartita.gestore.restituisciNomeGiocatori().get(i) + ": " + risultato);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Gli input non sono in un formato valido");
					i--;
					numRiga.setText("1");
					numColonna.setText("1");
				}
				frameInizializza.coloraPanel(Integer.parseInt(numRiga.getText()) - 1,
						Integer.parseInt(numColonna.getText()), Color.white);
				frameInizializza.rimuoviMancato(Integer.parseInt(numRiga.getText()) - 1,
						Integer.parseInt(numColonna.getText()));
				numRiga.setText("");
				numColonna.setText("");
			}
			j++;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBattagliaNavale = new JFrame();
		frmBattagliaNavale.setIconImage(
				Toolkit.getDefaultToolkit().getImage(BattagliaNavale.class.getResource("/boundary/icona.png")));
		frmBattagliaNavale.setResizable(false);
		frmBattagliaNavale.setTitle("Battaglia Navale");
		frmBattagliaNavale.setBounds(100, 100, 735, 392);
		frmBattagliaNavale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattagliaNavale.getContentPane().setLayout(null);

		panelGriglia = new JPanel();
		panelGriglia.setBackground(new Color(255, 255, 0));
		panelGriglia.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelGriglia.setBounds(199, 0, 519, 355);
		frmBattagliaNavale.getContentPane().add(panelGriglia);
		panelGriglia.setLayout(null);

		JPanel PanelMenu = new JPanel();
		PanelMenu.setBackground(new Color(0, 0, 255));
		PanelMenu.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		PanelMenu.setBounds(0, 0, 200, 355);
		frmBattagliaNavale.getContentPane().add(PanelMenu);

		JButton aggiungiNuovoGiocatore = new JButton("Inizializza Partita");
		aggiungiNuovoGiocatore.setFont(new Font("Tahoma", Font.BOLD, 10));
		aggiungiNuovoGiocatore.setForeground(Color.BLUE);
		aggiungiNuovoGiocatore.setBackground(Color.WHITE);
		aggiungiNuovoGiocatore.setBounds(20, 151, 158, 21);
		aggiungiNuovoGiocatore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmBattagliaNavale.setVisible(false);
				frameInizializza.frmInizializzaPartita.setVisible(true);
				
				frameInizializza.frmInizializzaPartita.setBounds(350, 150, 450, 200);
			}
		});
		PanelMenu.setLayout(null);
		PanelMenu.add(aggiungiNuovoGiocatore);

		JLabel etichettaGiocatori = new JLabel("Giocatori in partita:");
		etichettaGiocatori.setForeground(new Color(255, 255, 0));
		etichettaGiocatori.setFont(new Font("Verdana", Font.BOLD, 13));
		etichettaGiocatori.setBounds(26, 27, 152, 21);
		PanelMenu.add(etichettaGiocatori);

		JButton btnNewButton = new JButton("Mostra elenco partite");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(20, 189, 158, 21);
		PanelMenu.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Visualizza elenco posizioni");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBounds(20, 220, 158, 21);
		PanelMenu.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Visualizza stato partita");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(20, 251, 158, 21);
		PanelMenu.add(btnNewButton_2);
		tabellaGiocatori.setForeground(new Color(0, 0, 0));
		tabellaGiocatori.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		tabellaGiocatori.setBounds(20, 58, 158, 48);
		PanelMenu.add(tabellaGiocatori);
		tabellaGiocatori.setModel(new DefaultTableModel(
				new Object[][] {
					{"", },
					{"", },
					{"", },
				},
				new String[] {
					"Giocatori in Gara", "Colore Segnaposto"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		tabellaGiocatori.getColumnModel().getColumn(0).setPreferredWidth(113);
		tabellaGiocatori.getColumnModel().getColumn(1).setPreferredWidth(97);
		tabellaGiocatori.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabellaGiocatori.setShowGrid(false);
		tabellaGiocatori.setBounds(26, 66, 152, 48);
		

		
				
		

	}
}
