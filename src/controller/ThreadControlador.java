package controller;

import java.util.concurrent.Semaphore;

public class ThreadControlador extends Thread {

	private int comprador;
	private static int ingressos = 100;
	private Semaphore mutex;

	public ThreadControlador(int comprador, Semaphore mutex) {
		this.comprador = comprador;
		this.mutex = mutex;
	}

	@Override
	public void run() {
		Login();
	}

	private void Login() {
		int tempo = (int) ((Math.random() * 1951) + 50);// TODO Auto-generated method stub

		if (tempo <= 1000) {
			ProcessoCompra();
		} else {
			System.out.println("Senhor/a " + comprador + " excedeu o tempo de login " + tempo / 1000
					+ "s e nao podera continuar sua compra");
		}
	}

	private void ProcessoCompra() {
		int tempo2 = (int) ((Math.random() * 2001) + 1000);

		if (tempo2 < 2500) {
			try {
				mutex.acquire();
				ValidaCompra();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mutex.release();
			}

		} else {
			System.out.println("Falha na venda para " + comprador + "° comprador por estourar o tempo de sessão "
					+ tempo2 / 1000 + "s, tente novmente semana que vem");
		}

	}

	private void ValidaCompra() {
		int compra = (int) ((Math.random() * 4) + 1);
		
		ingressos -= compra;
		if (ingressos >= 0) {
			System.out.println("Venda de " + compra + " ingressos realizada com sucesso pra o/a " + comprador
					+ "° comprador, ainda restam " + ingressos + " ingressos");
		} else {
			ingressos += compra;
			System.out.println("Falha na venda pra " + comprador + "° comprador, infelizmente tente na proxima semana");
		}
	}

}
