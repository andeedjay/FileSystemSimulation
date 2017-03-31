/**
 *
 * @author andreis
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

public class User {
	private String username;
	private String parola;
	private String nume;
	private String prenume;
	private String creat;
	private String logat;
	
	public User(String username, String parola, String nume, String prenume) {
		this.username = username;
		this.parola = parola;
		this.nume = nume;
		this.prenume = prenume;
		Date data = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("'pe' dd.MM.yyyy 'la' HH:mm:ss");
		creat = ft.format(data);
	}
	
	@Override
	public String toString() {
		String s = "";
		if(this.logat != null)
			s = this.username + " " + this.parola + " " + this.nume + " " + this.prenume + " creat " + this.creat + " logat ultima data " + this.logat + "\n";
		else
			s = this.username + " " + this.parola + " " + this.nume + " " + this.prenume + " creat " + this.creat + " nelogat inca" + "\n";
		return s;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getParola() {
		return parola;
	}
	
	public String getNume() {
		return nume;
	}
	
	public String getPrenume() {
		return prenume;
	}
	
	public String getCreat() {
		return creat;
	}
	
	public String getLogat() {
		return logat;
	}
	
	public void setLogat(String logat) {
		this.logat = logat;
	}
}