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
		
		double probabilidadParcial; // P(St)
		
		switch (stI) {
		case Atacar:
			
			break;

		default:
			break;
		}
		
		switch (hI) {
		case Alto:
			
			break;

		default:
			break;
		}
		
		if (hnI) {
			
		}else {
			
		}
		
        if (pwI) {
			
		}else {
			
		}
		
        if (phI) {
			
		}else {
			
		}
		
		switch (wI) {
		case Armado:
			
			break;

		default:
			break;
		}
		
		switch (owI) {
		case Armado:
			
			break;

		default:
			break;
		}
		
		switch (neI) {
		case Muchos:
			
			break;

		default:
			break;
		}
		
		for (int i = 0; i < 6; ++i) {
			//calcular la probabilidad para cada valor de st1(Acciones)
			
			
			
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
