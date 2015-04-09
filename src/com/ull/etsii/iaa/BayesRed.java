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
	

	public double calcularProb (Acciones stI, VidaType hI, boolean hnI
			                   , boolean pwI, boolean phI, ArmaType wI
			                   , ArmaType owI, EnemigosType neI   ) {
		
		
		
		return 0.0;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
