/**
 * 
 */
package com.ull.etsii.iaa;

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
	
	double st = 1/7;
	
	double st1[][]={
        {0*0,1*0,2*0,3*0, 3*0, 3*0},
        {0*0,1*0,2*0,3*0, 3*0, 3*0},
        {0*0,1*0,2*0,3*0, 3*0, 3*0},
        {0*0,1*0,2*0,3*0, 3*0, 3*0},
        {0*0,1*0,2*0,3*0, 3*0, 3*0},
        {0*0,1*0,2*0,3*0, 3*0, 3*0}
	};
	double h[][]={
        {0*0,1*0},
        {0*0,1*0},
        {0*0,1*0},
        {0*0,1*0},
        {0*0,1*0},
        {0*0,1*0}
    };
	double hn[][]={
		{0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0}
	};
	double pw[][]={
		{0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0}
	};
	double ph[][]={
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0}
	};
	double w[][]={
		{0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0}
	};
	
	double ow[][]={
        {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0}
	};
	
	double ne[][]={
		{0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0},
	    {0*0,1*0}
	};
	

	public double[] calcularProb (Acciones stI, VidaType hI, boolean hnI
			                   , boolean pwI, boolean phI, ArmaType wI
			                   , ArmaType owI, EnemigosType neI   ) {
		double resultado [] = {0, 0, 0, 0, 0, 0};
		
		double probabilidadParcial = st;
		
		
		//BuscarArmas, BuscarEnergia, Explorar, Huir, DetectarPeligro
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
		default:
			indexSt = 5;
			break;
			
		}
		
		int indexH;
		switch (hI) {
		case Alto:
			indexH = 0;
			break;

		default:
			indexH = 1;
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

		default:
			indexOw = 1;
			break;
		}
		
		int indexNe;
		switch (neI) {
		case Muchos:
			indexNe = 0;
			break;

		default:
			indexNe = 1;
			break;
		}
		
		double resParcial2;
		for (int i = 0; i < 6; ++i) {
			//calcular la probabilidad para cada valor de st1(Acciones)
			resParcial2 = st *  st1[i][indexSt] * h[i][indexH] * hn[i][indexHn] * pw[i][indexPw] * ph[i][indexPh] 
					* w[i][indexWi] * ow[i][indexOw] * ne[i][indexNe];
			
			resultado[i] = resParcial2;
		}
		
		return resultado;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
