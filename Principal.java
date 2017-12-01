import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Principal extends JFrame {

	private JPanel contentPane;
	int nNotas, n0 =0;
	private JTextField textField;
	private JTextField textField_1;
	private Vector textFieldVector;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal(0, "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int getnNotas() {
		return nNotas;
	}

	public void setnNotas(int nNotas) {
		this.nNotas = nNotas;
	}

	private float calc(ArrayList<JTextField> x){
		float total =0;
		float nota =0;
		float notaTotal = 6 * getnNotas();
		float y;
		for(int i = 0; i< getnNotas(); i++){
			if(Float.parseFloat(x.get(i).getText()) > 10.0){
				JOptionPane p2 = new JOptionPane();
				Object[] options = { "OK" };
				p2.showOptionDialog(contentPane, "Impossivel, nota maior que 10", "Erro",
																		p2.DEFAULT_OPTION, p2.WARNING_MESSAGE,
																		null, options, options[0]);
			}else if(Float.parseFloat(x.get(i).getText()) < 0.0){
				JOptionPane p3 = new JOptionPane();
				Object[] options = { "OK" };
				p3.showOptionDialog(contentPane, "Impossivel, nota menor que 0", "Erro",
																		p3.DEFAULT_OPTION, p3.WARNING_MESSAGE,
																		null, options, options[0]);
			}else	if(x.get(i).getText().matches("^(\\d+\\.)?\\d+$")){
			 	nota = Float.parseFloat(x.get(i).getText());
				total += nota;
				if(Float.parseFloat(x.get(i).getText()) == 0.0){
					n0++;
				}
			}else{
				return 999;
			}
		}
		y = notaTotal - total;
		return y;
	}

	public Principal(int nNotas, String nome) {
		Aluno aluno = new Aluno(nome);

		if(nNotas != 0)
			setnNotas(nNotas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if(nNotas<9 && nNotas>4)
			setBounds(100, 100, 450, 195+(10*nNotas));
		else if(nNotas>=9)
			setBounds(100, 100, 450, 35*nNotas);
		else
			setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNotas = new JLabel("Notas " + aluno.getNome());
		lblNotas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNotas.setBounds(20, 10, 300, 22);
		contentPane.add(lblNotas);

		JLabel lblNote = new JLabel("Para notas quebradas, use '.'! (Ex: 6.5)");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNote.setBounds(20, 26, 200, 22);
		contentPane.add(lblNote);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(223, 85, 150, 28);
		contentPane.add(lblNewLabel_1);

		ArrayList<JTextField> textFieldVector = new ArrayList<JTextField>();
	  JTextField tf;
		for(int i = 0;i < nNotas;i++)
		{
			tf = new JTextField(String.valueOf(0));
			tf.setBounds(20, 50+(i*25), 86, 20);
	        textFieldVector.add(tf);
	        contentPane.add(tf);
		}
    contentPane.revalidate();
    contentPane.repaint();

		JButton btnNewButton_1 = new JButton("Calcular");
		btnNewButton_1.setBounds(221, 147, 89, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float xx = calc(textFieldVector);
				int k = n0 * 10;
				if(xx == 999){
					lblNewLabel_1.setText("Tente denovo!");
					JOptionPane p = new JOptionPane();
					Object[] options = { "OK" };
					p.showOptionDialog(contentPane, "Somente numeros porfavor!", "Erro",
																			p.DEFAULT_OPTION, p.WARNING_MESSAGE,
																			null, options, options[0]);
					n0 = 0;
				}else if(k < xx){
					lblNewLabel_1.setText("Reprovado!");
					n0 = 0;
				}else if(xx <= 0.0){
					lblNewLabel_1.setText("Aprovado!");
					n0 = 0;
				}else if(xx != 999 && xx < (6*getnNotas()) && xx > 0.0 && k > xx){
					lblNewLabel_1.setText(""+(new DecimalFormat("##.##").format(xx)));
					n0 = 0;
				}
			}
		});
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Voltar");
		btnNewButton_2.setBounds(330, 147, 89, 23);
		btnNewButton_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Main p2;
				p2 = new Main();
				p2.setVisible(true);
				contentPane.setVisible(false);
				Principal.this.dispose();
			}
		});
		contentPane.add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("<html>Nota necessaria <br>nas proximas avaliacoes:<br></html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(221, 10, 208, 89);
		contentPane.add(lblNewLabel);

	}
}
