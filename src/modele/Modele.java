package modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import modele.algo.Tabou;
import modele.etat.Tache;

public class Modele {
	int nbTache;
	int nbProc;
	int valMaxTache;
	
	public Modele(){
		int algo;
		String s="0";
		while(s.charAt(0) != '1' && s.charAt(0) != '2'){
			System.out.println("Bonjour quel algorithme voulez-vous utilisez ? tabou(1)/recuit(2)");
			 try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();
			        if(s==""){
			        	s="0";
			        }
			    }
			    catch(IOException e)
			    {
			        e.printStackTrace();
			    }
		}
		algo = Integer.parseInt(s);
		s = "-1";
		while(!estUnEntier(s) || Integer.parseInt(s)<0){
			System.out.println("Combien de Tache voulez-vous? (Generez aleatoirement)");
			 try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();
			        if(s==""){
			        	s="0";
			        }
			    }
			    catch(IOException e)
			    {
			        e.printStackTrace();
			    }
		}
		nbTache = Integer.parseInt(s);
		s = "-1";
		while(!estUnEntier(s) || Integer.parseInt(s)<0){
			System.out.println("Quel valeur max des taches voulez-vous?");
			 try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();
			        if(s==""){
			        	s="0";
			        }
			    }
			    catch(IOException e)
			    {
			        e.printStackTrace();
			    }
		}
		valMaxTache = Integer.parseInt(s);
		s = "-1";
		while(!estUnEntier(s) || Integer.parseInt(s)<0){
			System.out.println("Combien de proccesseurs voulez-vous?");
			 try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();
			        if(s==""){
			        	s="0";
			        }
			    }
			    catch(IOException e)
			    {
			        e.printStackTrace();
			    }
		}
		nbProc = Integer.parseInt(s);
		
		switch (algo){
			case 1:
				tabou();
				break;
			case 2:
				recuit();
				break;
		}
		
	}
	
	
	public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
	private boolean estUnFloat(String s) {
		try {
			Float.parseFloat(s);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
	
	private void recuit() {
		String s = "-1";
		while(!estUnFloat(s) || Float.parseFloat(s)<0){
			System.out.println("Quelle temperature voulez-vous?");
			 try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();
			        if(s==""){
			        	s="0";
			        }
			    }
			    catch(IOException e)
			    {
			        e.printStackTrace();
			    }
		}
		float Temperature = Float.parseFloat(s);
		 s = "-1";
			while(!estUnFloat(s) || Float.parseFloat(s)<0){
				System.out.println("Quelle borne inférieur voulez-vous?");
				 try{
				        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				        s = bufferRead.readLine();
				        if(s==""){
				        	s="0";
				        }
				    }
				    catch(IOException e)
				    {
				        e.printStackTrace();
				    }
			}
			float borneInf = Float.parseFloat(s);
			
		
			/*System.out.println();
			System.out.println("================Resultat====================");
			System.out.println("Nombre D'iteration: "+t.getNbIteration());
			System.out.println();
			System.out.println("Variation de la valeur de fonction objection");
			System.out.println(t.getlValeur());
			System.out.println();
			System.out.println("Repartion sur les différents proccesseurs");
			System.out.println(t.getMeilleurEtat());*/
	}

	


	private void tabou() {
		String s = "-1";
		while(!estUnEntier(s) || Integer.parseInt(s)<0){
			System.out.println("Quel taille de la liste tabou voulez-vous?");
			 try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();
			        if(s==""){
			        	s="0";
			        }
			    }
			    catch(IOException e)
			    {
			        e.printStackTrace();
			    }
		}
		int TailleTabTabou = Integer.parseInt(s);
		s = "-1";
		while(!estUnEntier(s) || Integer.parseInt(s)<0){
			System.out.println("Combien d'iteration sans changement maximum voulez-vous?");
			 try{
			        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			        s = bufferRead.readLine();
			        if(s==""){
			        	s="0";
			        }
			    }
			    catch(IOException e)
			    {
			        e.printStackTrace();
			    }
		}
		int maxIte = Integer.parseInt(s);
		Tabou t = new Tabou(generationTache(nbTache),nbProc,TailleTabTabou,maxIte);		
		System.out.println();
		System.out.println("================Resultat====================");
		System.out.println("Nombre D'iteration: "+t.getNbIteration());
		System.out.println();
		System.out.println("Variation de la valeur de fonction objection");
		System.out.println(t.getlValeur());
		System.out.println();
		System.out.println("Repartion sur les différents proccesseurs");
		System.out.println(t.getMeilleurEtat());
	}

	public ArrayList<Tache> generationTache(int nb){
		ArrayList<Tache> taches = new ArrayList<>();
		for(int i=0; i<nb; i++){
			taches.add(new Tache(0,(int)(Math.random() * valMaxTache)+1));
		}
		return taches;
		
	}


	public static void main(String[] args) {
		new Modele();
		
		
		
		
	}
}
