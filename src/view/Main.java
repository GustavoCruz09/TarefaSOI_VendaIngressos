package view;

import java.util.concurrent.Semaphore;

import controller.ThreadControlador;

public class Main {

	public static void main(String[] args) {
		Semaphore mutex = new Semaphore(1);
		
		for(int comprador = 0; comprador < 300; comprador++) {
			ThreadControlador bilheteria = new ThreadControlador(comprador, mutex);
			bilheteria.start();
		}

	}

}
