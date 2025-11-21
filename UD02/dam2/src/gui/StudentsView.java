package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import model.AppStudents;

/**
 * Interfaz gráfica principal de la aplicación.
 * <p>
 * Proporciona los controles para crear, eliminar y actualizar estudiantes,
 * así como una tabla para mostrar los registros existentes.
 * </p>
 */
public class StudentsView extends JFrame implements ActionListener {

	/** Panel raíz que contiene todos los elementos. */
	private JPanel contentPane;

	/** Campo de texto para el identificador del estudiante. */
	private JTextField txtID;

	/** Campo de texto para el nombre del estudiante. */
	private JTextField txtName;

	/** Campo de texto para los apellidos del estudiante. */
	private JTextField txtSurname;

	/** Campo de texto para la edad del estudiante. */
	private JTextField txtAge;

	/** Campo de texto para indicar si el estudiante ha aprobado. */
	private JTextField txtHasPassed;

	/** Botón para guardar (insertar) un estudiante. */
	private JButton btnSave;

	/** Botón para eliminar un estudiante por id. */
	private JButton btnDelete;

	/** Botón para actualizar un estudiante existente. */
	private JButton btnUpdate;

	/** Tabla que muestra los estudiantes. */
	private JTable table;

	/** Lógica de negocio / controlador de la aplicación. */
	private AppStudents app;

	/**
	 * Construye la vista principal y la inicializa.
	 *
	 * @param app instancia de {@link AppStudents} que maneja la lógica de la
	 *            aplicación y acciones solicitadas desde la UI.
	 */
	public StudentsView(AppStudents app) {
		this.app = app;

		setTitle("Students App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblTitle = new JLabel("Student Management System");
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(20, 11, 387, 60);
		contentPane.add(lblTitle);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(20, 71, 387, 284);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(31, 46, 36, 24);
		panel.add(lblId);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(21, 81, 46, 24);
		panel.add(lblName);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSurname.setBounds(10, 116, 74, 24);
		panel.add(lblSurname);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAge.setBounds(21, 154, 46, 24);
		panel.add(lblAge);

		JLabel lblHasPassed = new JLabel("Passsed");
		lblHasPassed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHasPassed.setBounds(21,192,74,24);
		panel.add(lblHasPassed);

		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtID.setBounds(102, 46, 263, 24);
		panel.add(txtID);
		txtID.setColumns(10);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtName.setColumns(10);
		txtName.setBounds(102, 81, 263, 24);
		panel.add(txtName);

		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSurname.setColumns(10);
		txtSurname.setBounds(102, 120, 263, 24);
		panel.add(txtSurname);

		txtAge = new JTextField();
		txtAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAge.setColumns(10);
		txtAge.setBounds(102, 155, 263, 24);
		panel.add(txtAge);

		txtHasPassed = new JTextField();
		txtHasPassed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHasPassed.setColumns(10);
		txtHasPassed.setBounds(102, 190, 263, 24);
		panel.add(txtHasPassed);

		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(78, 230, 89, 23);
		btnSave.addActionListener(this);
		panel.add(btnSave);

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(177, 230, 89, 23);
		btnUpdate.addActionListener(this);
		panel.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(276, 230, 89, 23);
		btnDelete.addActionListener(this);
		panel.add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(417, 71, 467, 284);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Name", "Surname", "Age", "Passed" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Integer.class, String.class };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.setAlignmentX(LEFT_ALIGNMENT);
		scrollPane.setViewportView(table);
	}

	/**
	 * Maneja los eventos de los botones Save, Update y Delete.
	 * <p>
	 * Valida los campos necesarios y delega las acciones al objeto {@link AppStudents}.
	 * </p>
	 *
	 * @param e evento de acción generado por la UI
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSave) {
			String id = txtID.getText();
			String name = txtName.getText();
			String surname = txtSurname.getText();
			String age = txtAge.getText();
			String hasPassed = txtHasPassed.getText();
			if (id.isEmpty()|| name.isEmpty() || surname.isEmpty() || age.isEmpty() || hasPassed.isEmpty()) {
				showMessage("POR FAVOR COMPLETA TODOS LOS CAMPOS");
				return;
			}
			app.enrollStudent(id, name, surname, Integer.parseInt(age), hasPassed);
		} else if (e.getSource() == btnDelete) {
			String id = txtID.getText();
			app.dropStudent(id);
		} else if (e.getSource() == btnUpdate) {
			String id = txtID.getText();
			String name = txtName.getText();
			String surname = txtSurname.getText();
			String age = txtAge.getText();
			String hasPassed = txtHasPassed.getText();
			if (id.isEmpty()|| name.isEmpty() || surname.isEmpty() || age.isEmpty() || hasPassed.isEmpty()) {
				showMessage("POR FAVOR COMPLETA TODOS LOS CAMPOS");
				return;
			}
			app.updateStudent(id, name, surname, Integer.parseInt(age), hasPassed );
		}
	}

	/**
	 * Muestra un mensaje emergente al usuario.
	 *
	 * @param msg mensaje a mostrar
	 */
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	/**
	 * Limpia los campos del formulario.
	 */
	public void clear() {
		txtID.setText("");
		txtName.setText("");
		txtSurname.setText("");
		txtAge.setText("");
		txtHasPassed.setText("");
	}

	/**
	 * Carga los estudiantes desde el controlador para mostrarlos en la tabla.
	 */
	public void load() {
		app.showAllStudents();
	}

	/**
	 * Refresca la vista de la tabla limpiándola y recargando los datos.
	 */
	public void refresh() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		clear();
		load();
	}

	/**
	 * Añade una fila con la información de un estudiante a la tabla.
	 *
	 * @param id identificador del estudiante
	 * @param name nombre del estudiante
	 * @param surname apellidos del estudiante
	 * @param age edad del estudiante
	 * @param hasPassed texto que indica si ha aprobado
	 */
	public void addStudent(String id, String name, String surname, int age, String hasPassed) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.addRow(new Object[] { id, name, surname, age, hasPassed });
	}
}
