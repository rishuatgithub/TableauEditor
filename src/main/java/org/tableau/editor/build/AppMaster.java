package org.tableau.editor.build;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AppMaster {

	public static void main(String[] args) {
		
		JFrame jf = new JFrame("Tableau Editor");
		
		final JTextField tf = new JTextField();
		tf.setBounds(50,50,100,40);
		
		JButton jb = new JButton("Click");		
		jb.setBounds(130, 100, 100, 40);		
		
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf.setText("Hello Rishu");
			}
		});
		
		
		jf.add(jb);
		jf.add(tf);		
		
		jf.setSize(400, 400);
		jf.setLayout(null);
		jf.setVisible(true);
	}

}
