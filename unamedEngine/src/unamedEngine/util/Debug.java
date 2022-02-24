package unamedEngine.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Debug {
	private static int maxLogCount = 100;
	private static int maxListCount = 5;
	private static LinkedList<String> log = new LinkedList<String>();
	private static LinkedList<Object> list = new LinkedList<Object>();

	public static boolean log(String value) {
		if (log.size() > maxLogCount) {
			log.removeFirst();
		}
		log.add(value);
		System.out.println(log.getLast());
		return true;
	}

	public static void Show(Object value) {
		if (!list.contains(value) && list.size() < maxListCount) {
			list.add(value);
		}
	}

	public static LinkedList<Object> GetList() {
		return list;
	}

	static JFrame frame;

	public static void Init() {
		frame = new JFrame("FrameDemo");
		frame.setPreferredSize(new Dimension(100, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout gridLayout = new GridLayout(0, 1);
		frame.setLayout(gridLayout);
		frame.pack();
	}

	public static void Show() {

		frame.setVisible(true);
	}

	public static LinkedHashMap<String, JLabel> labels = new LinkedHashMap<String, JLabel>();

	public static void AddText(String name, String value) {

	}

	public static void SetText(String name, int value) {
		SetText(name, "" + value);
	}

	public static void SetText(String name, long value) {
		SetText(name, "" + value);
	}

	public static void SetText(String name, String value) {
		JLabel label = labels.get(name);
		if (label == null) {
			label = new JLabel("test");
			label.setText(name + ":" + value);
			labels.put(name, label);
		}

		if (label != null) {
			label.setText(name + ":" + value);
			frame.getContentPane().add(label, FlowLayout.LEFT);

		}
	}

	public static void Destroy() {

		frame.dispose();
	}
}