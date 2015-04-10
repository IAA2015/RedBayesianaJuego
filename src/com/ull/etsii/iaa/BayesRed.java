/**
 * 
 */
package com.ull.etsii.iaa;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.WindowConstants;

/**
 * @author
 *
 */
public class BayesRed {

	public enum Acciones { 
		Atacar, BuscarArmas, BuscarEnergia, Explorar, Huir, DetectarPeligro
	}

	public enum VidaType {
		Alto, Bajo
	}

	public enum ArmaType {
		Armado, Desarmado
	}

	public enum EnemigosType {
		Muchos, Pocos
	}

	static double st = 1 / 6.0;

	static double st1[][]={
		{0.89996,  1e-5,        1e-5,        1e-5,        0.1,         1e-5},
		{0.49998,  0.49998,     1e-5,        1e-5,        1e-5,        1e-5},
		{0.49998,  1e-5,        0.49998,     1e-5,        1e-5,        1e-5},
		{0.49998,  1e-5,        1e-5,        0.49998,     1e-5,        1e-5},
		{0.49998,  1e-5,        1e-5,        1e-5,        0.49998,     1e-5},
		{0.49998,  1e-5,        1e-5,        1e-5,        1e-5,        0.49998}
	};
	static double h[][]={
		{0.999,  0.001},
		{0.9,    0.1},
		{0.001,  0.999},
		{0.9,    0.1},
		{0.2,    0.8},
		{0.9,    0.1}
	};
	static double hn[][]={
		{0.6,  0.4},
		{0.5,  0.5},
		{0.5,  0.5},
		{0.9,  0.1},
		{0.5,  0.5},
		{0.9,  0.1}
	};
	static double pw[][]={
		{0.5,  0.5},
		{0.9,  0.1},
		{0.5,  0.5},
		{0.9,  0.1},
		{0.5,  0.5},
		{0.6,  0.4}
	};
	static double ph[][]={
		{0.5,  0.5},
		{0.5,  0.5},
		{0.9,  0.1},
		{0.6,  0.4},
		{0.5,  0.5},
		{0.6,  0.4}
	};
	static double w[][]={
		{0.999,  0.001},
		{0.2,    0.8},
		{0.5,    0.5},
		{0.1,    0.9},
		{0.2,    0.8},
		{0.5,    0.5}
	};

	static double ow[][]={
		{0.3,  0.7},
		{0.6,  0.4},
		{0.5,  0.5},
		{0.5,  0.5},
		{0.6,  0.4},
		{0.5,  0.5}
	};

	static double ne[][]={
		{0.5,  0.5},
		{0.6,  0.4},
		{0.6,  0.4},
		{0.2,  0.8},
		{0.8,  0.2},
		{0.9,  0.1}
	};


	public static double[] calcularProb (Acciones stI, VidaType hI, boolean hnI
			, boolean pwI, boolean phI, ArmaType wI
			, ArmaType owI, EnemigosType neI   ) {
		double resultado [] = new double[6];

		int indexSt;
		switch (stI) {
		case Atacar:
			indexSt = 0;
			break;
		case BuscarArmas:
			indexSt = 1;
			break;
		case BuscarEnergia:
			indexSt = 2;
			break;
		case Explorar:
			indexSt = 3;
			break;
		case Huir:
			indexSt = 4;
			break;
		case DetectarPeligro:
			indexSt = 5;
			break;
		default:
			indexSt = -1;
			break;
		}

		int indexH;
		switch (hI) {
		case Alto:
			indexH = 0;
			break;
		case Bajo:
			indexH = 1;
			break;
		default:
			indexH = -1;
			break;
		}

		int indexHn;
		if (hnI) {
			indexHn = 0;
		}else {
			indexHn = 1;
		}

		int indexPw;
		if (pwI) {
			indexPw = 0;
		}else {
			indexPw = 1;
		}

		int indexPh;
		if (phI) {
			indexPh = 0;
		}else {
			indexPh = 1;
		}

		int indexWi;
		switch (wI) {
		case Armado:
			indexWi = 0;
			break;
		default:
			indexWi = 1;
			break;
		}

		int indexOw;
		switch (owI) {
		case Armado:
			indexOw = 0;
			break;
		case Desarmado:
			indexOw = 1;
			break;
		default:
			indexOw = -1;
			break;
		}

		int indexNe;
		switch (neI) {
		case Muchos:
			indexNe = 0;
			break;
		case Pocos:
			indexNe = 1;
			break;
		default:
			indexNe = -1;
			break;
		}

		double resParcial2;
		double norm = 0;

		for (int i = 0; i < 6; ++i) {
			resParcial2 = st *  st1[indexSt][i] * h[i][indexH] * hn[i][indexHn] * pw[i][indexPw] * ph[i][indexPh] 
					* w[i][indexWi] * ow[i][indexOw] * ne[i][indexNe];

			resultado[i] = resParcial2;

			norm += resParcial2;

		}

		for (int i = 0; i < 6; ++i) {
			resultado[i] = resultado[i] / norm;
		}

		return resultado;
	}


	/**
	 * @param args
	 */
	 public static void main(String[] args) {
		JFrame ventanaP = new JFrame("Red Baysiana para el comportamiento de bots.");
		final JComboBox<Acciones> accionesP = new JComboBox<Acciones>();
		final JComboBox<VidaType> vidaAct = new JComboBox<VidaType>();
		final JComboBox<Boolean> oirSonido = new JComboBox<Boolean>();
		final JComboBox<Boolean> armaCer = new JComboBox<Boolean>();
		final JComboBox<Boolean> vidaCer = new JComboBox<Boolean>();
		final JComboBox<ArmaType> arma = new JComboBox<ArmaType>();
		final JComboBox<ArmaType> armaEnem = new JComboBox<ArmaType>();
		final JComboBox<EnemigosType> numEnem = new JComboBox<EnemigosType>();
		
		JLabel accionesP_Tit = new JLabel("Ultima accion");
		JLabel vidaAct_Tit = new JLabel("Nivel salud");
		JLabel oirSonido_Tit = new JLabel("Oir ruido");
		JLabel armaCer_Tit = new JLabel("Pack armamento cerca");
		JLabel vidaCer_Tit = new JLabel("Pack vida cerca");
		JLabel arma_Tit = new JLabel("Arma bot");
		JLabel armaEnem_Tit = new JLabel("Arma enemigos");
		JLabel numEnem_Tit = new JLabel("Cantidad enemigos");

		
		JPanel accionesP_Pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel vidaAct_Pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel oirSonido_Pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel armaCer_Pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel vidaCer_Pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel arma_Pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel armaEnem_Pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel numEnem_Pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		accionesP_Pan.add(accionesP_Tit);
		accionesP_Pan.add(accionesP);
		vidaAct_Pan.add(vidaAct_Tit);
		vidaAct_Pan.add(vidaAct);
		oirSonido_Pan.add(oirSonido_Tit);
		oirSonido_Pan.add(oirSonido);
		armaCer_Pan.add(armaCer_Tit);
		armaCer_Pan.add(armaCer);
		vidaCer_Pan.add(vidaCer_Tit);
		vidaCer_Pan.add(vidaCer);
		arma_Pan.add(arma_Tit);
		arma_Pan.add(arma);
		armaEnem_Pan.add(armaEnem_Tit);
		armaEnem_Pan.add(armaEnem);
		numEnem_Pan.add(numEnem_Tit);
		numEnem_Pan.add(numEnem);

		
		
		
		accionesP.addItem(Acciones.Atacar);
		accionesP.addItem(Acciones.BuscarArmas);
		accionesP.addItem(Acciones.BuscarEnergia);
		accionesP.addItem(Acciones.Explorar);
		accionesP.addItem(Acciones.Huir);
		accionesP.addItem(Acciones.DetectarPeligro);
		vidaAct.addItem(VidaType.Alto);
		vidaAct.addItem(VidaType.Bajo);
		oirSonido.addItem(true);
		oirSonido.addItem(false);
		armaCer.addItem(true);
		armaCer.addItem(false);
		vidaCer.addItem(true);
		vidaCer.addItem(false);
		arma.addItem(ArmaType.Armado);
		arma.addItem(ArmaType.Desarmado);
		armaEnem.addItem(ArmaType.Armado);
		armaEnem.addItem(ArmaType.Desarmado);
		numEnem.addItem(EnemigosType.Muchos);
		numEnem.addItem(EnemigosType.Pocos);

		accionesP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("Cambiar ultima accion realizada: " + accionesP.getSelectedItem());

				double [] resultado = calcularProb((Acciones) accionesP.getSelectedItem()
						, (VidaType) vidaAct.getSelectedItem()
						, (boolean) oirSonido.getSelectedItem()
						, (boolean) armaCer.getSelectedItem()
						, (boolean) vidaCer.getSelectedItem()
						, (ArmaType) arma.getSelectedItem()
						, (ArmaType) armaEnem.getSelectedItem()
						, (EnemigosType) numEnem.getSelectedItem());


				DecimalFormat df = new DecimalFormat("0.00000000");

	
				
				System.out.println("El resultado de probabilidades es:");
				System.out.println("Atacar:.......... " + df.format(resultado[0] * 100));
				System.out.println("BuscarArmas:..... " + df.format(resultado[1] * 100));
				System.out.println("BuscarEnergia:... " + df.format(resultado[2] * 100));
				System.out.println("Explorar:........ " + df.format(resultado[3] * 100));
				System.out.println("Huir:............ " + df.format(resultado[4] * 100));
				System.out.println("DetectarPeligro:. " + df.format(resultado[5] * 100));

			}
		});
		vidaAct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("Cambiar nivel de vida actual: " + vidaAct.getSelectedItem());

				double [] resultado = calcularProb((Acciones) accionesP.getSelectedItem()
						, (VidaType) vidaAct.getSelectedItem()
						, (boolean) oirSonido.getSelectedItem()
						, (boolean) armaCer.getSelectedItem()
						, (boolean) vidaCer.getSelectedItem()
						, (ArmaType) arma.getSelectedItem()
						, (ArmaType) armaEnem.getSelectedItem()
						, (EnemigosType) numEnem.getSelectedItem());


				DecimalFormat df = new DecimalFormat("0.00000000");

				System.out.println("El resultado de probabilidades es:");
				System.out.println("Atacar:.......... " + df.format(resultado[0] * 100));
				System.out.println("BuscarArmas:..... " + df.format(resultado[1] * 100));
				System.out.println("BuscarEnergia:... " + df.format(resultado[2] * 100));
				System.out.println("Explorar:........ " + df.format(resultado[3] * 100));
				System.out.println("Huir:............ " + df.format(resultado[4] * 100));
				System.out.println("DetectarPeligro:. " + df.format(resultado[5] * 100));

			}
		});
		oirSonido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("Cambiar escuchado sonido: " + oirSonido.getSelectedItem());

				double [] resultado = calcularProb((Acciones) accionesP.getSelectedItem()
						, (VidaType) vidaAct.getSelectedItem()
						, (boolean) oirSonido.getSelectedItem()
						, (boolean) armaCer.getSelectedItem()
						, (boolean) vidaCer.getSelectedItem()
						, (ArmaType) arma.getSelectedItem()
						, (ArmaType) armaEnem.getSelectedItem()
						, (EnemigosType) numEnem.getSelectedItem());


				DecimalFormat df = new DecimalFormat("0.00000000");

				System.out.println("El resultado de probabilidades es:");
				System.out.println("Atacar:.......... " + df.format(resultado[0] * 100));
				System.out.println("BuscarArmas:..... " + df.format(resultado[1] * 100));
				System.out.println("BuscarEnergia:... " + df.format(resultado[2] * 100));
				System.out.println("Explorar:........ " + df.format(resultado[3] * 100));
				System.out.println("Huir:............ " + df.format(resultado[4] * 100));
				System.out.println("DetectarPeligro:. " + df.format(resultado[5] * 100));

			}
		});
		armaCer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("Cambiado pack armamento cercano: " + armaCer.getSelectedItem());

				double [] resultado = calcularProb((Acciones) accionesP.getSelectedItem()
						, (VidaType) vidaAct.getSelectedItem()
						, (boolean) oirSonido.getSelectedItem()
						, (boolean) armaCer.getSelectedItem()
						, (boolean) vidaCer.getSelectedItem()
						, (ArmaType) arma.getSelectedItem()
						, (ArmaType) armaEnem.getSelectedItem()
						, (EnemigosType) numEnem.getSelectedItem());


				DecimalFormat df = new DecimalFormat("0.00000000");

				System.out.println("El resultado de probabilidades es:");
				System.out.println("Atacar:.......... " + df.format(resultado[0] * 100));
				System.out.println("BuscarArmas:..... " + df.format(resultado[1] * 100));
				System.out.println("BuscarEnergia:... " + df.format(resultado[2] * 100));
				System.out.println("Explorar:........ " + df.format(resultado[3] * 100));
				System.out.println("Huir:............ " + df.format(resultado[4] * 100));
				System.out.println("DetectarPeligro:. " + df.format(resultado[5] * 100));

			}
		});
		vidaCer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("Cambiado pack de vida cercano: " + vidaCer.getSelectedItem());

				double [] resultado = calcularProb((Acciones) accionesP.getSelectedItem()
						, (VidaType) vidaAct.getSelectedItem()
						, (boolean) oirSonido.getSelectedItem()
						, (boolean) armaCer.getSelectedItem()
						, (boolean) vidaCer.getSelectedItem()
						, (ArmaType) arma.getSelectedItem()
						, (ArmaType) armaEnem.getSelectedItem()
						, (EnemigosType) numEnem.getSelectedItem());


				DecimalFormat df = new DecimalFormat("0.00000000");

				System.out.println("El resultado de probabilidades es:");
				System.out.println("Atacar:.......... " + df.format(resultado[0] * 100));
				System.out.println("BuscarArmas:..... " + df.format(resultado[1] * 100));
				System.out.println("BuscarEnergia:... " + df.format(resultado[2] * 100));
				System.out.println("Explorar:........ " + df.format(resultado[3] * 100));
				System.out.println("Huir:............ " + df.format(resultado[4] * 100));
				System.out.println("DetectarPeligro:. " + df.format(resultado[5] * 100));

			}
		});
		arma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("Cambiado armamento bot: " + arma.getSelectedItem());

				double [] resultado = calcularProb((Acciones) accionesP.getSelectedItem()
						, (VidaType) vidaAct.getSelectedItem()
						, (boolean) oirSonido.getSelectedItem()
						, (boolean) armaCer.getSelectedItem()
						, (boolean) vidaCer.getSelectedItem()
						, (ArmaType) arma.getSelectedItem()
						, (ArmaType) armaEnem.getSelectedItem()
						, (EnemigosType) numEnem.getSelectedItem());


				DecimalFormat df = new DecimalFormat("0.00000000");

				System.out.println("El resultado de probabilidades es:");
				System.out.println("Atacar:.......... " + df.format(resultado[0] * 100));
				System.out.println("BuscarArmas:..... " + df.format(resultado[1] * 100));
				System.out.println("BuscarEnergia:... " + df.format(resultado[2] * 100));
				System.out.println("Explorar:........ " + df.format(resultado[3] * 100));
				System.out.println("Huir:............ " + df.format(resultado[4] * 100));
				System.out.println("DetectarPeligro:. " + df.format(resultado[5] * 100));

			}
		});
		armaEnem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("Cambiado armamento enemigos: " + armaEnem.getSelectedItem());

				double [] resultado = calcularProb((Acciones) accionesP.getSelectedItem()
						, (VidaType) vidaAct.getSelectedItem()
						, (boolean) oirSonido.getSelectedItem()
						, (boolean) armaCer.getSelectedItem()
						, (boolean) vidaCer.getSelectedItem()
						, (ArmaType) arma.getSelectedItem()
						, (ArmaType) armaEnem.getSelectedItem()
						, (EnemigosType) numEnem.getSelectedItem());


				DecimalFormat df = new DecimalFormat("0.00000000");

				System.out.println("El resultado de probabilidades es:");
				System.out.println("Atacar:.......... " + df.format(resultado[0] * 100));
				System.out.println("BuscarArmas:..... " + df.format(resultado[1] * 100));
				System.out.println("BuscarEnergia:... " + df.format(resultado[2] * 100));
				System.out.println("Explorar:........ " + df.format(resultado[3] * 100));
				System.out.println("Huir:............ " + df.format(resultado[4] * 100));
				System.out.println("DetectarPeligro:. " + df.format(resultado[5] * 100));

			}
		});
		numEnem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("----------------------------------------------------------------");
				System.out.println("Cambiado numero enemigos: " + numEnem.getSelectedItem());
				
				double [] resultado = calcularProb((Acciones) accionesP.getSelectedItem()
						                           , (VidaType) vidaAct.getSelectedItem()
						                           , (boolean) oirSonido.getSelectedItem()
						                           , (boolean) armaCer.getSelectedItem()
						                           , (boolean) vidaCer.getSelectedItem()
						                           , (ArmaType) arma.getSelectedItem()
						                           , (ArmaType) armaEnem.getSelectedItem()
						                           , (EnemigosType) numEnem.getSelectedItem());


				DecimalFormat df = new DecimalFormat("0.00000000");

				System.out.println("El resultado de probabilidades es:");
				System.out.println("Atacar:.......... " + df.format(resultado[0] * 100));
				System.out.println("BuscarArmas:..... " + df.format(resultado[1] * 100));
				System.out.println("BuscarEnergia:... " + df.format(resultado[2] * 100));
				System.out.println("Explorar:........ " + df.format(resultado[3] * 100));
				System.out.println("Huir:............ " + df.format(resultado[4] * 100));
				System.out.println("DetectarPeligro:. " + df.format(resultado[5] * 100));
				
				

			}
		});

		ventanaP.getContentPane().setLayout(new BoxLayout(ventanaP.getContentPane(), BoxLayout.Y_AXIS));
		ventanaP.getContentPane().add(accionesP_Pan);
		ventanaP.getContentPane().add(vidaAct_Pan);
		ventanaP.getContentPane().add(oirSonido_Pan);
		ventanaP.getContentPane().add(armaCer_Pan);
		ventanaP.getContentPane().add(vidaCer_Pan);
		ventanaP.getContentPane().add(arma_Pan);
		ventanaP.getContentPane().add(armaEnem_Pan);
		ventanaP.getContentPane().add(numEnem_Pan);

		ventanaP.pack();
		ventanaP.setVisible(true);
		ventanaP.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		double [] resultado = calcularProb(Acciones.Atacar, VidaType.Bajo, true, false, false
				, ArmaType.Armado, ArmaType.Desarmado, EnemigosType.Pocos);


		DecimalFormat df = new DecimalFormat("0.00000000");
		
		System.out.println("+ Ejemplo de prueba(Atacar, Bajo, true, fals, false, Armado, Desarmado, Pocos): ");

		System.out.println("   El resultado de probabilidades es:");
		System.out.println("   Atacar:.......... " + df.format(resultado[0] * 100));
		System.out.println("   BuscarArmas:..... " + df.format(resultado[1] * 100));
		System.out.println("   BuscarEnergia:... " + df.format(resultado[2] * 100));
		System.out.println("   Explorar:........ " + df.format(resultado[3] * 100));
		System.out.println("   Huir:............ " + df.format(resultado[4] * 100));
		System.out.println("   DetectarPeligro:. " + df.format(resultado[5] * 100));

	 }

}
