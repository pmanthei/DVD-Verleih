package com.fa11.dvdverleih.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.fa11.dvdverleih.datenhaltung.DatenhaltungFactory;
import com.fa11.dvdverleih.datenhaltung.DatenhaltungType;
import com.fa11.dvdverleih.fachkonzept.Fachkonzept;

public class StarterGUI extends JFrame {

	private static final long serialVersionUID = -6090691783379043704L;
	private JPanel contentPane;
	private JLabel lblBitteTreffenSie;
	private JLabel lblOberflche;
	private JLabel lblDatenhaltung;
	private JComboBox<String> cmbUserInterface;
	private JComboBox<String> cmbData;
	private JButton btnStarten;

	/**
	 * StarterGUI testweise starten
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StarterGUI frame = new StarterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * StarterGUI erstellen
	 */
	public StarterGUI() {
		setResizable(false);
		setTitle("DVD-Verleih");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 140);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);

		this.lblBitteTreffenSie = new JLabel("Bitte treffen Sie eine Auswahl");
		this.lblBitteTreffenSie.setFont(new Font("Segoe UI Semilight",
				Font.PLAIN, 20));
		GridBagConstraints gbc_lblBitteTreffenSie = new GridBagConstraints();
		gbc_lblBitteTreffenSie.anchor = GridBagConstraints.WEST;
		gbc_lblBitteTreffenSie.gridwidth = 2;
		gbc_lblBitteTreffenSie.insets = new Insets(0, 0, 5, 0);
		gbc_lblBitteTreffenSie.gridx = 0;
		gbc_lblBitteTreffenSie.gridy = 0;
		this.contentPane.add(this.lblBitteTreffenSie, gbc_lblBitteTreffenSie);

		this.lblOberflche = new JLabel("Oberfl\u00E4che");
		GridBagConstraints gbc_lblOberflche = new GridBagConstraints();
		gbc_lblOberflche.insets = new Insets(0, 0, 5, 5);
		gbc_lblOberflche.gridx = 0;
		gbc_lblOberflche.gridy = 1;
		this.contentPane.add(this.lblOberflche, gbc_lblOberflche);

		this.lblDatenhaltung = new JLabel("Datenhaltung");
		GridBagConstraints gbc_lblDatenhaltung = new GridBagConstraints();
		gbc_lblDatenhaltung.insets = new Insets(0, 0, 5, 0);
		gbc_lblDatenhaltung.gridx = 1;
		gbc_lblDatenhaltung.gridy = 1;
		this.contentPane.add(this.lblDatenhaltung, gbc_lblDatenhaltung);

		this.cmbUserInterface = new JComboBox<String>();
		this.cmbUserInterface.setModel(new DefaultComboBoxModel<String>(
				new String[] { "GUI", "TUI" }));
		GridBagConstraints gbc_cmbUserInterface = new GridBagConstraints();
		gbc_cmbUserInterface.insets = new Insets(0, 0, 5, 5);
		gbc_cmbUserInterface.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbUserInterface.gridx = 0;
		gbc_cmbUserInterface.gridy = 2;
		this.contentPane.add(this.cmbUserInterface, gbc_cmbUserInterface);

		this.cmbData = new JComboBox<String>();
		this.cmbData.setModel(new DefaultComboBoxModel<String>(new String[] {
				"XML", "SQLite" }));
		GridBagConstraints gbc_cmbData = new GridBagConstraints();
		gbc_cmbData.insets = new Insets(0, 0, 5, 0);
		gbc_cmbData.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbData.gridx = 1;
		gbc_cmbData.gridy = 2;
		this.contentPane.add(this.cmbData, gbc_cmbData);

		this.btnStarten = new JButton("Starten");
		this.btnStarten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// GUI
				if(cmbUserInterface.getSelectedIndex() == 0){
					if(cmbData.getSelectedIndex() == 0){
						JFrame gui = new GUI(new Fachkonzept(DatenhaltungFactory.getDatenhaltung(DatenhaltungType.xml)));
						gui.setVisible(true);
						setVisible(false);
					}
					else if (cmbData.getSelectedIndex() == 1){
						JFrame gui = new GUI(new Fachkonzept(DatenhaltungFactory.getDatenhaltung(DatenhaltungType.sqlite)));
						gui.setVisible(true);
						setVisible(false);
					}
				}
				// TUI
				else if (cmbUserInterface.getSelectedIndex() == 1){
//					JOptionPane.showMessageDialog(null, "Hier wird noch gearbeitet!",
//							"Eine sch�ne Fehlermeldung",
//							JOptionPane.INFORMATION_MESSAGE,
//							null);
					
					if(cmbData.getSelectedIndex() == 0){
						setVisible(false);
						dispose();
						new TUI(new Fachkonzept(DatenhaltungFactory.getDatenhaltung(DatenhaltungType.xml)));
					}
					else if (cmbData.getSelectedIndex() == 1){
						setVisible(false);
						dispose();
						new TUI(new Fachkonzept(DatenhaltungFactory.getDatenhaltung(DatenhaltungType.sqlite)));
					}
				}
			}
		});
		GridBagConstraints gbc_btnStarten = new GridBagConstraints();
		gbc_btnStarten.gridwidth = 2;
		gbc_btnStarten.insets = new Insets(0, 0, 0, 5);
		gbc_btnStarten.gridx = 0;
		gbc_btnStarten.gridy = 3;
		this.contentPane.add(this.btnStarten, gbc_btnStarten);
		pack();
		setLocationRelativeTo(null);
	}

}