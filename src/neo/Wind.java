package neo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.event.InputMethodListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Wind {
	JTextArea BblBod;
	JTextArea Vvod;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wind window = new Wind();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Wind() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 648, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Vvod = new JTextArea();
		Vvod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				key_pressed();
			}
		});
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		frame.getContentPane().add(Vvod);

		BblBod = new JTextArea();
		JScrollPane scroll = new JScrollPane(BblBod);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		frame.getContentPane().add(scroll);
	}

	private void key_pressed() {
		System.out.println("колбек работат");
		String s = Vvod.getText();
//		Matcher m = Pattern.compile("[а-я]* ").matcher(s);
		int startPoz, endPoz;
		startPoz = endPoz = Vvod.getCaretPosition();
		while (startPoz >= 0 && startPoz < s.length()
				&& (s.toCharArray()[startPoz] != ' ' && s.toCharArray()[startPoz] != '\n'))
			startPoz--;
		while (
				endPoz >= 0 && 
				endPoz < s.length() && 
				(s.toCharArray()[endPoz] != ' ' && s.toCharArray()[endPoz] != '\n'))
			endPoz++;
		try {

			//BblBod.setText(" " + startPoz + "; " + endPoz + "\n");
			if (startPoz != endPoz)	
				BblBod.setText(ne.Pars(s.substring(startPoz, endPoz)));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
