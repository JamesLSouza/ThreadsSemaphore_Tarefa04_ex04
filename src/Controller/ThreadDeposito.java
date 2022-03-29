package Controller;

import java.util.concurrent.Semaphore;

public class ThreadDeposito extends Thread {
	private int id;
	private double saldo;
	private double Transacao;
	private Semaphore limitacao;

	public ThreadDeposito(int idConta, double saldoConta, double valorTransacao, Semaphore limitacao) {
		this.id = idConta;
		this.saldo = saldoConta;
		this.Transacao = valorTransacao;
		this.limitacao = limitacao;
	}

	
	public void run() {
		try {
			limitacao.acquire();
			creditar();
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			limitacao.release();
		}
	}

	public void creditar() {
		System.out.format("Conta %d - Saldo Anterior: R$ %.2f Deposito no valor de: R$ %.2f Novo saldo: R$ %.2f%n",id, saldo, Transacao, saldo + Transacao);
		this.saldo += Transacao;
	}

}