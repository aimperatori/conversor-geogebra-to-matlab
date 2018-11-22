package pkgGrafico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.GenericArrayType;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;

public class Menu implements ActionListener, ChangeListener {
	
	JButton botao;		
	JTextArea entrada;		
	JTextArea saida;	
	JRadioButton cor[];
	ButtonGroup onlyOne;
	String corSaida = "";
	JColorChooser paletaCores;
	float red;
	float green;
	float blue;	
	
	public Menu() {
		JFrame frame = new JFrame(); //cria o frame onde será colocado os objetos
		
		Container c = frame.getContentPane(); //adiciona o botao no frame			
		c.setLayout(new BorderLayout(30, 30)); //configura o layout
		
		Box boxes[] = new Box[2];
		
		boxes[0] = Box.createVerticalBox();		
		boxes[1] = Box.createHorizontalBox();		
				
		Font fonte = new Font("Arial", Font.BOLD, 26); //Define fonte e tamanho
		JLabel texto = new JLabel("Conversor GeoGebra para Matlab");
		texto.setHorizontalAlignment(SwingConstants.CENTER);
		texto.setFont(fonte); //Passa a conf de fonte para o texto	
		c.add(texto, BorderLayout.NORTH); //Adiciona o texto ao frame
		
		botao = new JButton("Converter"); //cria e define nome do botao
		botao.addActionListener(this); //chama o metodo de execução do botao
		c.add(BorderLayout.SOUTH, botao);		
		
		entrada = new JTextArea();		
		entrada.setLineWrap(true); // configura mudança automática de linha
		entrada.setWrapStyleWord(true); // determina que as mudança de linha seja definida pelas palavras
		entrada.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		entrada.setSize(600,600);		
		
		saida = new JTextArea();
		saida.setLineWrap(true); // configura mudan¸ca autom´atica de linha
		saida.setWrapStyleWord(true); // determina que as mudan¸ca de linha seja definida pelas palavras					
		saida.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		saida.setSize(600,600);
		
		cor = new JRadioButton[6];
		cor[0] = new JRadioButton("Azul");
		boxes[0].add(cor[0]);
		cor[1] = new JRadioButton("Amarelo");
		boxes[0].add(cor[1]);
		cor[2] = new JRadioButton("Vermelho");
		boxes[0].add(cor[2]);
		cor[3] = new JRadioButton("Preto");
		boxes[0].add(cor[3]);
		cor[4] = new JRadioButton("Verde");
		boxes[0].add(cor[4]);
		cor[5] = new JRadioButton("Paleta de Cores");
		boxes[0].add(cor[5]);		
		
		paletaCores = new JColorChooser();
		paletaCores.setEnabled(false);
		
		boxes[0].add(paletaCores);
		
		paletaCores.getSelectionModel().addChangeListener(this);		
		
		AbstractColorChooserPanel[] panels=paletaCores.getChooserPanels();
        for(AbstractColorChooserPanel p:panels){
            String displayName=p.getDisplayName();
            switch (displayName) {
                case "HSV":
                	paletaCores.removeChooserPanel(p);
                    break;
                case "HSL":
                	paletaCores.removeChooserPanel(p);
                    break;
                case "CMYK":
                	paletaCores.removeChooserPanel(p);
                    break;
            }            
        }
        
		onlyOne = new ButtonGroup();
		onlyOne.add(cor[0]);
		onlyOne.add(cor[1]);
		onlyOne.add(cor[2]);
		onlyOne.add(cor[3]);
		onlyOne.add(cor[4]);
		onlyOne.add(cor[5]);		
		
		TratadorRadioButton trat = new TratadorRadioButton(); //registra os ouvintes para as caixas de marcacao		
		cor[0].addItemListener(trat);
		cor[1].addItemListener(trat);
		cor[2].addItemListener(trat);
		cor[3].addItemListener(trat);
		cor[4].addItemListener(trat);
		cor[5].addItemListener(trat);
				
		boxes[1].add(new JScrollPane(entrada, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)); //coloca a barra de rolagem e adiciona ao container
		
		boxes[1].add(boxes[0]); //adiciona os radioButtons		
		
		boxes[1].add(new JScrollPane(saida, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)); //coloca a barra de rolagem e adiciona ao container
		
		c.add(boxes[1]);
		
		//Configurações da Janela principal
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para finalizar a aplicação quando fechar
		frame.setSize(1200, 800);
		frame.setResizable(false);
		frame.setVisible(true); //para aparecer a janela
	}
	
	public class TratadorRadioButton implements ItemListener {				
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource()==cor[0]) {
				corSaida = ",'b'";
				paletaCores.setEnabled(false);
			} else if(e.getSource()==cor[1]) {
				corSaida = ",'y'";
				paletaCores.setEnabled(false);				
			}
			else if(e.getSource()==cor[2]) {
				corSaida = "',r'";
				paletaCores.setEnabled(false);
			}
			else if(e.getSource()==cor[3]) {
				corSaida = ",'k'";
				paletaCores.setEnabled(false);				
			}
			else if(e.getSource()==cor[4]) {
				corSaida = ",'g'";
				paletaCores.setEnabled(false);
			}
			else if(e.getSource()==cor[5]) {
				paletaCores.setEnabled(true);				
				corSaida = ",["+ red/255 +" "+ green/255 +" "+ blue/255 +"]";
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) { //executa o botao
		String aux = (String) entrada.getText(); //passa o texto não convertido da jtextarea para uma string para a conversão
		
		if (aux.length()==0) //testa se o aux tem texto, senao tiver retorna void
			return;
		
		char recebe[];
		recebe = new char[9000];
		
		//inicializa o vetor
		recebe[0] = 'A';
		recebe[1] = ' ';
		recebe[2] = '=';
		recebe[3] = ' ';
		recebe[4] = '[';	
		
		//DEBUG
		//System.out.println(recebe);
		//System.out.println(recebe.length);
		
		//parte X
		int i = 0;
		int j = 5;
		
		do {
			if(aux.charAt(i)=='(') {
				do {
					i++;					
					recebe[j] = aux.charAt(i);					
					j++;					
				} while (aux.charAt(i)!=',');				
			} else 
				i++;
		} while(aux.length()!=i);
		
		i = 5;		
		while(recebe[i]!=',') { //coloca o primeiro valor no ultimo, para fechar a imagem
			recebe[j] = recebe[i];
			j++;
			i++;
		}
		recebe[j] = ';';
		j++;
		
		// parte Y
		i = 0;
		do {			
			if(aux.charAt(i)==',') {
				i+=2;
				while (aux.charAt(i)!=')') {
					recebe[j] = aux.charAt(i);
					i++;					
					j++;					
				}
				recebe[j] = ',';
				j++;
			} else 
				i++;
		} while(aux.length()!=i);
		
		i = 5;
		do {
			if (recebe[i]==';') {
				i++;
				do {
					recebe[j] = recebe[i];
					i++;
					j++;
				} while(recebe[i]!=',');
				recebe[j] = ']';
				break;
			} else
				i++;
		} while(true);
		
				
		String sai = new String(recebe); //passa o array de char para uma string	
		sai = sai.trim(); //exclui os espaços em no inicio e no fim da string
		sai = sai.concat("\nfill(A(1,:), A(2,:)" +corSaida+ ")");
		sai = sai.concat("\nA=M*A;");
		
		sai = sai.concat("\nfill(A(1,:), A(2,:)" +corSaida+ ")\n");
		sai = sai.concat(new String(recebe)); //passa o array de char para uma string	
		sai = sai.trim(); //exclui os espaços em no inicio e no fim da string
		sai = sai.concat("\nA=N*A;");
		
		sai = sai.concat("\nfill(A(1,:), A(2,:)" +corSaida+ ")\n");
		sai = sai.concat(new String(recebe)); //passa o array de char para uma string
		sai = sai.trim(); //exclui os espaços em no inicio e no fim da string		
		
		sai = sai.concat("\nA=O*A;");
		sai = sai.concat("\nfill(A(1,:), A(2,:)" +corSaida+ ")");
				
		saida.setText(sai); //passa o texto convertido da string para a jtextarea de saida
	}
	
	public static void main(String[] args) {		
		
		new Menu();
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Color cor = new Color(1, 1, 1);
		cor = paletaCores.getColor();
		red = cor.getRed();
		green = cor.getGreen();
		blue = cor.getBlue();
		System.out.println(" "+ red +" "+ green +" "+ blue);
		corSaida = ",["+ red/255 +" "+ green/255 +" "+ blue/255 +"]";
	}
}