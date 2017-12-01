// 	Universidade Federal da Fronteira Sul - Chapecó;
//	Ciencia da Computação;
//	Programação I - 2017/1;
// 	Guilherme Konopatzki;
// 	Trabalho manipulação de objetos com interface grafica;
// 	Recebe as notas dos trabalhos e das provas feitas até o momento e, como resultado,
//	mostra quanto o aluno precisa de nota nas próximas avaliações, para ser aprovado na media;
//	A media a ser alcançada é 6.0;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.JOptionPane;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Principal p1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Quantos trabalhos havera no semestre?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 470, 41);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel2 = new JLabel("Aluno:");
		lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel2.setBounds(20, 46, 200, 40);
		contentPane.add(lblNewLabel2);

		JLabel lblNewLabel3 = new JLabel("Trabalhos:");
		lblNewLabel3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel3.setBounds(20, 76, 200, 40);
		contentPane.add(lblNewLabel3);

		textField_1 = new JTextField();
		textField_1.setBounds(94, 86, 60, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JTextField textField_2 = new JTextField();
		textField_2.setBounds(94, 56, 120, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		Button button = new Button("Next");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_1.getText().matches("^(\\d+\\.)?\\d+$")){
					p1 = new Principal(Integer.parseInt(textField_1.getText()), textField_2.getText());
					p1.setVisible(true);
					contentPane.setVisible(false);
					dispose();
				}else{
					JOptionPane p = new JOptionPane();
					Object[] options = { "OK" };
					p.showOptionDialog(contentPane, "Somente numeros por favor!", "Erro",
																			p.DEFAULT_OPTION, p.WARNING_MESSAGE,
																			null, options, options[0]);
				}
			}
		});
		button.setBounds(238, 86, 70, 22);
		contentPane.add(button);

	}
}
